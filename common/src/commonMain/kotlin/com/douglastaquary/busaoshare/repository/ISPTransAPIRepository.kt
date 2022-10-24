package com.douglastaquary.busaoshare.repository

import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.model.Trip
import kotlinx.coroutines.flow.Flow

interface ISPTransAPIRepository {

    suspend fun authenticationRequest(): Boolean
    suspend fun fetchTrips(searchName: String): Result<List<Trip>>
    suspend fun fetchTripAsFlow(searchName: String): Flow<List<Trip>>
}