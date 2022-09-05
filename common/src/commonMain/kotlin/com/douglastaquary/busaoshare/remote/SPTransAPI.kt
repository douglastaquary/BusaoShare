package com.douglastaquary.busaoshare.remote

import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.ArrivalOfVehiclesPerTrip
import com.douglastaquary.busaoshare.model.Result
import com.douglastaquary.busaoshare.model.Trip
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.request
import io.ktor.client.statement.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SPTransAPI(
    private val client: HttpClient,
    val baseUrl: String = "http://api.olhovivo.sptrans.com.br/v2.1"
) {
    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    suspend fun authentication(): Boolean {
        return client.post {
            url("$baseUrl/Login/Autenticar")
            parameter("token", Companion.API_TOKEN)
        }.body()
    }

    suspend fun fetchTrips(searchText: String): List<Trip> {
        return client.get {
            url("$baseUrl/Linha/Buscar")
            parameter("termosBusca", "$searchText")
            Logger.a("Requesting fetchTrips - params: $searchText ")
        }.body()
    }

    suspend fun fetchTripArrives(tripNumber: Int): List<ArrivalOfVehiclesPerTrip> {
        return client.get {
            url("$baseUrl/Previsao/Linha")
            parameter("codigoLinha", "$tripNumber")
        }.body()
    }

    companion object {
        private const val API_TOKEN = "3fea87313d3ab87ec1862fd941a69ab93334b3a39a1cd7d6a2dadb35b7e2aa78"
    }
}