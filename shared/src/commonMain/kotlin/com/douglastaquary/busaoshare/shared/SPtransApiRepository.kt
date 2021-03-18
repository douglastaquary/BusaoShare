package com.douglastaquary.busaoshare.shared

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Trip (
    @SerialName("cl") val tripId: Int,
    @SerialName("lc") val withoutSecondaryTerminal: Boolean,
    @SerialName("lt") val firstPartOfTheSign: String,
    @SerialName("tl") val secondPartOfTheSign: Int,
    @SerialName("sl") val travelDestination: Int,
    @SerialName("tp") val mainTerminal: String,
    @SerialName("ts") val secondaryTerminal: String,
)

@ExperimentalCoroutinesApi
class SPTransAPIRepository  {
    private val sptransApi = SPtransApi
    private var isAuthenticated: Boolean = false

    @Throws(Exception::class)
    private suspend fun authenticationRequest() : Boolean {
        val result = sptransApi.authentication()
        return result
    }

    fun pollTripsUpdates(text: String): Flow<List<Trip>> = flow {
        while (true) {
            if (isAuthenticated) {
                val trips = sptransApi.fetchTrips(searchText = "$text")
                emit(trips)
                delay(POLL_INTERVAL)
            } else {
                if (authenticationRequest()) {
                    isAuthenticated = true
                    val tripList = sptransApi.fetchTrips(searchText = "$text")
                    emit(tripList)
                    delay(POLL_INTERVAL)
                }
            }
        }
    }

    companion object {
        private const val POLL_INTERVAL = 10000L
    }
}
