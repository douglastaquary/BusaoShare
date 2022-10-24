package com.douglastaquary.busaoshare.android.ui.screens.search.viewModels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.android.ui.state.UiState
import com.douglastaquary.busaoshare.android.ui.screens.search.SearchWidgetState
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.repository.SPTransAPIRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class SearchTripViewModel(
    private val repository: SPTransAPIRepository
) : ViewModel() {

    val tripListState = MutableStateFlow<UiState<List<Trip>>>(UiState.Empty)
    var isAuthenticated = MutableStateFlow<Boolean>(false)

    private val _searchWidgetState: MutableState<SearchWidgetState> =
        mutableStateOf(value = SearchWidgetState.CLOSED)
    val searchWidgetState: State<SearchWidgetState> = _searchWidgetState

    private val _searchTextState: MutableState<String> =
        mutableStateOf(value = "")
    val searchTextState: State<String> = _searchTextState


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

    fun updateSearchWidgetState(newValue: SearchWidgetState) {
        _searchWidgetState.value = newValue
    }

    fun updateSearchTextState(newValue: String) {
        _searchTextState.value = newValue
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

}