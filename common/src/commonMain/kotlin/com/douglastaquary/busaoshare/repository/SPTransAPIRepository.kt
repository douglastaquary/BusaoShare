package com.douglastaquary.busaoshare.repository

import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.remote.SPTransAPI
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import com.douglastaquary.busaoshare.model.Result
import com.rickclephas.kmp.nativecoroutines.NativeCoroutineScope
import kotlinx.coroutines.flow.*

open class SPTransAPIRepository: KoinComponent {
    private val sptransApi: SPTransAPI = get()
    private var isAuthenticated: Boolean = false

    @NativeCoroutineScope
    private val mainScope: CoroutineScope = MainScope()

    suspend fun authenticationRequest(): Boolean {
        Logger.i { "authenticationRequest" }
        return sptransApi.authentication()
    }

    suspend fun fetchTrips(searchName: String): Result<List<Trip>> {
        try {
            if (isAuthenticated) {
                val trips = sptransApi.fetchTrips(searchText = "$searchName")
                Logger.i { "fetchTrips, trips, size = ${trips.size}" }
                return Result.Success(trips)
            } else {
                Logger.i { "RequestingAuthentication" }
                if (authenticationRequest()) {
                    isAuthenticated = true
                    Logger.i { "Authenticated!" }
                    val tripList = sptransApi.fetchTrips(searchText = "$searchName")
                    Logger.i { "fetchTrips, trips, size = ${tripList.size}" }
                    return Result.Success(tripList)
                }
            }
        } catch (e: Exception) {
            println(e)
            return Result.Error(e)
        }
        return Result.Success(emptyList())
    }

    fun fetchTripAsFlow(searchName: String): Flow<List<Trip>> {
        Logger.i { "fetchTripAsFlow() - searchName: $searchName" }
        return flow {
            while (true) {
                val tripList = sptransApi.fetchTrips(searchText = "$searchName")
                emit(tripList)
                Logger.d { tripList.toString() }
                delay(POLL_INTERVAL)
            }
        }
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}