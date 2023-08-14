package com.dev.taquarylab.busaoshare.repository

import co.touchlab.kermit.Logger
import com.dev.taquarylab.busaoshare.model.Result
import com.dev.taquarylab.busaoshare.model.Stop
import com.dev.taquarylab.busaoshare.model.Trip
import com.dev.taquarylab.busaoshare.remote.ISPTransAPI
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SPTransAPIRepository(
    private val sptransApi: ISPTransAPI
) : ISPTransAPIRepository {

    override suspend fun authenticationRequest(): Boolean {
        Logger.i { "authenticationRequest" }
        return sptransApi.authentication()
    }

    override suspend fun fetchTrips(searchName: String): Result<List<Trip>> {
        return try {
            Logger.i { "Fetch Trips Request" }
            sptransApi.fetchTrips(searchText = searchName).run {
                Logger.i { "fetchTrips, trips, size = $size" }
                Result.Success(this)
            }
        } catch (e: Exception) {
            println(e)
            Result.Error(e)
        }
    }


    override suspend fun fetchStops(tripID: String): Result<List<Stop>> {
        return try {
            Logger.i { "Fetch Stop Request" }
            sptransApi.fetchStopsPerTrip(tripID = tripID).run {
                Logger.i { "fetchStopsPerTrip, stops, size = $size" }
                Result.Success(this)
            }
        } catch (e: Exception) {
            println(e)
            Result.Error(e)
        }
    }

    override suspend fun fetchTripAsFlow(searchName: String): Flow<List<Trip>> = flow {
        Logger.i { "fetchTripAsFlow() - searchName: $searchName" }
        emit(emptyList())
        while (true) {
            val tripList = sptransApi.fetchTrips(searchText = searchName)
            Logger.i { "fetchTripAsFlow() - result: ${tripList.toString()}" }
            emit(tripList)
            Logger.d { tripList.toString() }
            delay(POLL_INTERVAL)
        }
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}