package com.douglastaquary.busaoshare.remote

import co.touchlab.kermit.Logger
import com.douglastaquary.busaoshare.model.ArrivalOfVehiclesPerTrip
import com.douglastaquary.busaoshare.model.Stop
import com.douglastaquary.busaoshare.model.Trip
import io.ktor.client.HttpClient
import io.ktor.client.call.*
import io.ktor.client.request.*

class SPTransAPI(
    private val client: HttpClient,
    private val baseUrl: String = "http://api.olhovivo.sptrans.com.br/v2.1"
) : ISPTransAPI {

    private var cookie: String? = null

    override suspend fun authentication(): Boolean {
        return client.post {
            url("$baseUrl/Login/Autenticar")
            parameter("token", API_TOKEN)
        }.also {
            cookie = it.headers["set-cookie"]
        }.body()
    }

    override suspend fun fetchTrips(searchText: String): List<Trip> {
        return client.get {
            url("$baseUrl/Linha/Buscar")
            parameter("termosBusca", searchText)
            header("Cookie", cookie)
            Logger.a("Requesting fetchTrips - params: $searchText")
        }.body()
    }

    override suspend fun fetchStopsPerTrip(tripID: String): List<Stop> {
        return client.get {
            url("$baseUrl/Parada/BuscarParadasPorLinha")
            parameter("codigoLinha", tripID)
            header("Cookie", cookie)
            Logger.a("Requesting stops - params: $tripID")
        }.body()
    }

    override suspend fun fetchTripArrives(tripNumber: Int): List<ArrivalOfVehiclesPerTrip> {
        return client.get {
            url("$baseUrl/Previsao/Linha")
            parameter("codigoLinha", tripNumber)
            header("Cookie", cookie)
        }.body()
    }

    companion object {
        private const val API_TOKEN = "3fea87313d3ab87ec1862fd941a69ab93334b3a39a1cd7d6a2dadb35b7e2aa78"
    }
}