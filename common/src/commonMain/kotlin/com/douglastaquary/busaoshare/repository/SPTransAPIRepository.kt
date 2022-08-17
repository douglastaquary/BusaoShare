package com.douglastaquary.busaoshare.repository

import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.remote.SPTransAPI
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

open class SPTransAPIRepository: KoinComponent {
    private val sptransApi: SPTransAPI = get()
    private var isAuthenticated: Boolean = false

    private val coroutineScope: CoroutineScope = MainScope()

    suspend fun authenticationRequest(): Boolean {
        Logger.i { "authenticationRequest" }
        return sptransApi.authentication()
    }

//    suspend fun fetchTrips(searchName: String): Flow<List<Trip>> = flow {
//        try {
//            if (isAuthenticated) {
//                val trips = sptransApi.fetchTrips(searchText = "$searchName")
//                Logger.i { "fetchTrips, trips, size = ${trips.size}" }
//                return Result.Success(trips)
//            } else {
//                Logger.i { "RequestingAuthentication" }
//                if (authenticationRequest()) {
//                    isAuthenticated = true
//                    Logger.i { "Authenticated!" }
//                    val tripList = sptransApi.fetchTrips(searchText = "$searchName")
//                    Logger.i { "fetchTrips, trips, size = ${tripList.size}" }
//                    return Result.Success(tripList)
//                }
//            }
//        } catch (e: Exception) {
//            println(e)
//            return Result.Error(e)
//        }
//        return Result.Success(emptyList())
//    }

    suspend fun fetchTrips(searchName: String): Flow<List<Trip>> = flow {
        while (true) {
            println("fetchTrips, trip name = $searchName")
            val trips = fetchTripShareInfo(searchName)
            emit(trips)
            delay(POLL_INTERVAL)
        }
    }

    suspend fun fetchTripShareInfo(tripName: String) : List<Trip> {
        val tripList = sptransApi.fetchTrips(searchText = "$tripName")
        return tripList
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}