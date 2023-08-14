package com.dev.taquarylab.busaoshare.repository

import com.dev.taquarylab.busaoshare.model.Result
import com.dev.taquarylab.busaoshare.model.Stop
import com.dev.taquarylab.busaoshare.model.Trip
import kotlinx.coroutines.flow.Flow

interface ISPTransAPIRepository {

    suspend fun authenticationRequest(): Boolean
    suspend fun fetchTrips(searchName: String): Result<List<Trip>>
    suspend fun fetchTripAsFlow(searchName: String): Flow<List<Trip>>
    suspend fun fetchStops(tripID: String): Result<List<Stop>>
}