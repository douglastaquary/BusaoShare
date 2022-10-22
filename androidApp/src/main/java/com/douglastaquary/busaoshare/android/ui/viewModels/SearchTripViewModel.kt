package com.douglastaquary.busaoshare.android.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.repository.ISPTransAPIRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

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
    private val repository: ISPTransAPIRepository
) : ViewModel() {

    val tripListState = MutableStateFlow<UiState<List<Trip>>>(UiState.Empty)
    private val _search = MutableStateFlow(null as String?)
    val search: StateFlow<String?> = _search
    var isAuthenticated = MutableStateFlow<Boolean>(false)

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAuthorization()
        }
    }

    private suspend fun getAuthorization() {
        repository.authenticationRequest().also {
            isAuthenticated.value = it
            Logger.a("Authorization result = $it")
        }
    }

    fun getTrips(tripName: String) {
        tripListState.value = UiState.Loading
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchTrips(tripName).also {
                tripListState.value = when (it) {
                    is Result.Success -> UiState.Success(it.data)
                    is Result.Error -> UiState.Error(it.e)
                }
            }
        }
    }

    private fun tripsMock() {
        tripListState.value = UiState.Loading
        val trip1 = Trip(
            firstPartOfTheSign = "Terminal Bandeira",
            secondaryTerminal = "Terminal jabaquara",
            withoutSecondaryTerminal = false,
            secondPartOfTheSign = 25,
            travelDestination = 23456,
            mainTerminal = "Centro - Brás",
            id = 0,
            tripId = "1"
        )
        tripListState.value = UiState.Success(listOf(trip1))
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}