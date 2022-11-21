package com.douglastaquary.busaoshare.remote

import com.douglastaquary.busaoshare.model.ArrivalOfVehiclesPerTrip
import com.douglastaquary.busaoshare.model.Stop
import com.douglastaquary.busaoshare.model.Trip

interface ISPTransAPI {

    suspend fun authentication(): Boolean
    suspend fun fetchTrips(searchText: String): List<Trip>
    suspend fun fetchTripArrives(tripNumber: Int): List<ArrivalOfVehiclesPerTrip>
    suspend fun fetchStopsPerTrip(tripID: String): List<Stop>
}