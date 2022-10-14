package com.douglastaquary.busaoshare.android.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.repository.SPTransAPIRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.*

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

    init {
        viewModelScope.launch {
            getAuthorization()
        }
    }

    suspend fun getAuthorization() {
        val result = repository.authenticationRequest()
        isAuthenticated.value = result
        Logger.a("Authorization result = $result")
    }

    suspend fun getTrips(tripName: String) {
        println("getTrips, tripName = $tripName")
        tripListState.value = UiState.Loading
        var trip1 = Trip(
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

//        val result = repository.fetchTrips(tripName)
//        tripListState.value = when (result) {
//            is Result.Success -> UiState.Success(result.data)
//            is Result.Error -> UiState.Error(result.e)
//        }
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}
