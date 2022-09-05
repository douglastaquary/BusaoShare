package com.douglastaquary.busaoshare.android.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.repository.SPTransAPIRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

data class TripListUiState(
    val items: List<Trip> = emptyList(),
    val isLoading: Boolean = false,
    val userMessage: String? = null
)

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    object Empty : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}

@ExperimentalCoroutinesApi
class SearchTripViewModel(
    private val repository: SPTransAPIRepository
    ) : ViewModel() {

    val tripListState = MutableStateFlow<UiState<List<Trip>>>(UiState.Empty)
    private val _search = MutableStateFlow(null as String?)
    val search: StateFlow<String?> = _search
    var isAuthenticated = MutableStateFlow<Boolean>(false)

    suspend fun getAuthorization() {
        viewModelScope.launch {
            val result = repository.authenticationRequest()
            isAuthenticated.value = result
            Logger.a("Authorization result = $result")
        }
    }

    fun poolTrips(tripName: String = "centro") {
        viewModelScope.launch {
            if (isAuthenticated.value) {
                getTrips(tripName)
            } else {
                getAuthorization().apply {
                    getTrips(tripName)
                }
            }
        }
    }

    fun getTrips(tripName: String) {
        viewModelScope.launch {
            val result = repository.fetchTrips(tripName)
            tripListState.value = when (result) {
                is Result.Success -> UiState.Success(result.data)
                is Result.Error -> UiState.Error(result.e)
            }
        }
    }

    fun tripsAsFlow(query: String) {
        viewModelScope.launch {
            repository.fetchTripAsFlow(query)
                .catch { ex ->
                    Logger.a("${ex.message.toString()}")
                    tripListState.value = UiState.Error(ex as Exception)
                }.collect { trips ->
                    tripListState.value = UiState.Success(trips)
                }
        }
    }
}
