package com.dev.taquarylab.busaoshare.remote

import com.dev.taquarylab.busaoshare.model.ArrivalOfVehiclesPerTrip
import com.dev.taquarylab.busaoshare.model.Stop
import com.dev.taquarylab.busaoshare.model.Trip

interface ISPTransAPI {

    suspend fun authentication(): Boolean
    suspend fun fetchTrips(searchText: String): List<Trip>
    suspend fun fetchTripArrives(tripNumber: Int): List<ArrivalOfVehiclesPerTrip>
    suspend fun fetchStopsPerTrip(tripID: String): List<Stop>
}