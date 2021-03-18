package com.douglastaquary.busaoshare.androidApp

import androidx.lifecycle.*
import co.touchlab.kermit.Kermit
import com.douglastaquary.busaoshare.shared.SPTransAPIRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.flow.map
import java.util.*

class TripListViewModel(
    private val spTransAPIRepository: SPTransAPIRepository,
    private val logger: Kermit,
    private val coroutineScope: CoroutineScope = MainScope()
) : ViewModel() {

    val trip = MutableLiveData<String>("")

    val tripsUpdated = trip.switchMap { spTransAPIRepository.pollTripsUpdates(it).asLiveData() }

    fun setTrip(trip: String) {
        this.trip.value = trip
    }
}