package com.douglastaquary.busaoshare.shared
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlin.native.concurrent.ThreadLocal

@ThreadLocal
object SPtransApi {
    private const val baseUrl = "http://api.olhovivo.sptrans.com.br/v2.1"
    private const val API_TOKEN = "f89300e7615320c82cb1d9911d26c3dba054338ba9c39059bc2d9a414091ece8"

    private val nonStrictJson = Json { isLenient = true; ignoreUnknownKeys = true }

    private val client = HttpClient {
        install(JsonFeature) {
            serializer = KotlinxSerializer(nonStrictJson)
        }

        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.INFO
        }
    }

    suspend fun authentication(): Boolean {
        return client.post {
            url{
                protocol = URLProtocol.HTTP
                host = baseUrl
                method = HttpMethod.Post
                encodedPath = "/Login/Autenticar"
                parameter("token", API_TOKEN)
            }
        }
    }

    suspend fun fetchTrips(searchText: String): List<Trip> {
        return client.get {
            url {
                protocol = URLProtocol.HTTPS
                host = baseUrl
                method = HttpMethod.Get
                encodedPath = "/Linha/Buscar"
                parameter("termosBusca", "$searchText")
            }
        }
    }

}