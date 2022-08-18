package com.douglastaquary.busaoshare.repository

import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.remote.SPTransAPI
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import com.douglastaquary.busaoshare.model.Result

open class SPTransAPIRepository: KoinComponent {
    private val sptransApi: SPTransAPI = get()
    private var isAuthenticated: Boolean = false

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
}