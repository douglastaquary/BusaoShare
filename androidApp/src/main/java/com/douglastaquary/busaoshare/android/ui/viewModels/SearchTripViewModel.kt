package com.douglastaquary.busaoshare.android.ui.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.repository.SPTransAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}

@ExperimentalCoroutinesApi
class SearchTripViewModel(
    private val repository: SPTransAPIRepository
    ) : ViewModel() {

    val tripListState = MutableStateFlow<UiState<List<Trip>>>(UiState.Loading)
    var isAuthenticated = MutableStateFlow<Boolean>(false)
    var currentTripName = MutableStateFlow<String>("")
    var currentTrip = MutableStateFlow<Trip?>(null)

    fun poolTrips(tripName: String = "interlagos") {
        viewModelScope.launch {
            val result =  repository.fetchTrips(tripName)
            println("Trip List - $result ")
        }
    }

//    private fun pollTripList(tripId: String) {
//        println("pollTripList, trip name = $tripId")
//        viewModelScope.launch {
//            val result = repository.pollTrips(tripId)
//            tripListState.value = when (result) {
//                is Result.Success -> UiState.Success(result.data)
//                is Result.Error -> UiState.Error(result.exception)
//            }
//        }
//    }

    fun setTripName(tripName: String) {
        currentTripName.value = tripName
    }
}
