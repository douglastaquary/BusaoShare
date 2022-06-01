package com.douglastaquary.busaoshare.android.ui.viewModels

import androidx.lifecycle.ViewModel
import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.repository.SPTransAPIRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    data class Success<out T : Any>(val data: T) : UiState<T>()
    data class Error(val exception: Exception) : UiState<Nothing>()
}

@ExperimentalCoroutinesApi
class SearchTripViewModel(
    private val repository: SPTransAPIRepository
    ) : ViewModel() {

    val tripId = MutableStateFlow<String>("")
    val tripSearchListState = MutableStateFlow<UiState<List<Trip>>>(UiState.Loading)
    var currentTrip = MutableStateFlow<Trip?>(null)
    val tripList = currentTrip.filterNotNull().flatMapLatest { searchTrip(it.tripId) }

    private fun searchTrip(tripId: String): Flow<List<Trip>> = flow {
        emit(emptyList())
        while (true) {
            val result = repository.fetchTrips(tripId)
            if (result is Result.Success) {
                Logger.d { result.data.toString() }
                emit(result.data)
            }
        }
    }
}
