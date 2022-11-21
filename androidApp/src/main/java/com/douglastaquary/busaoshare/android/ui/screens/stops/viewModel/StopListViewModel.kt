package com.douglastaquary.busaoshare.android.ui.screens.stops.viewModel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.douglastaquary.busaoshare.android.ui.state.UiState
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.model.Stop
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.repository.ISPTransAPIRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class StopListViewModel(
    private val repository: ISPTransAPIRepository,
) : ViewModel() {

    val stopListState = MutableStateFlow<UiState<List<Stop>>>(UiState.Empty)
    val tripCurrent = MutableStateFlow<Trip?>(null)

    init {
        getStopList("33934")
    }

    fun getStopList(trip: String) {
        stopListState.value = UiState.Loading
        //tripCurrent.value = trip
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchStops(trip).also {
                stopListState.value = when (it) {
                    is Result.Success -> UiState.Success(it.data)
                    is Result.Error -> UiState.Error(it.e)
                }
            }
        }
    }

    private fun stopsMock() {
        stopListState.value = UiState.Loading
        val stop1 = Stop(
            id = 123455,
            stopName = "AFONSO BRAZ B/C1",
            stopLocationAddress = "R ARMINDA/ R BALTHAZAR DA VEIGA",
            latitude = -23.592938,
            longitude = -46.672727
        )
        val stop2 = Stop(
            id = 0,
            stopName = "Av.Cupece - Jardim Prudencia",
            stopLocationAddress = "Av.Cupece, 1784",
            latitude = -23.592938,
            longitude = -46.672727
        )

        val stopListMock = listOf<Stop>(stop1, stop2)

        stopListState.value = UiState.Success(stopListMock)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}