package com.douglastaquary.busaoshare.remote

import com.douglastaquary.busaoshare.model.Trip
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class SPTransAPI(
    private val client: HttpClient,
    private val baseUrl: String = "http://api.olhovivo.sptrans.com.br/v2.1"
) {

    suspend fun authentication(): Boolean {
        return client.post {
            url {
                host = baseUrl
                parameter("token", Companion.API_TOKEN)
            }
        }.body()
    }

    suspend fun fetchTrips(searchText: String): List<Trip> {
        return client.get {
            url {
                parameter("termosBusca", "$searchText")
            }
        }.body()
    }

    companion object {
        private const val API_TOKEN = "f89300e7615320c82cb1d9911d26c3dba054338ba9c39059bc2d9a414091ece8"
    }
}