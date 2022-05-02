package com.douglastaquary.busaoshare.repository

import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.Trip
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.remote.SPTransAPI
import kotlinx.coroutines.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import kotlin.time.ExperimentalTime

@OptIn(ExperimentalTime::class)
open class SPTransAPIRepository: KoinComponent {
    private val sptransApi: SPTransAPI = get()
    private var isAuthenticated: Boolean = false

    private val coroutineScope: CoroutineScope = MainScope()

    @Throws(Exception::class)
    suspend fun authenticationRequest(): Boolean {
        Logger.i { "fetchAndStoreBusStops" }
        return sptransApi.authentication()
    }

    fun fetchTrips(searchName: String, success: (List<Trip>) -> Unit) {
        coroutineScope.launch {
            if (isAuthenticated) {
                val trips = sptransApi.fetchTrips(searchText = "$searchName")
                success(trips)
            } else {
                if (authenticationRequest()) {
                    isAuthenticated = true
                    val tripList = sptransApi.fetchTrips(searchText = "$searchName")
                    success(tripList)
                }
            }
        }
    }
}