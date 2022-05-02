package com.douglastaquary.busaoshare.di

import com.douglastaquary.busaoshare.remote.SPTransAPI
import com.douglastaquary.busaoshare.repository.SPTransAPIRepository
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import kotlin.time.ExperimentalTime

@ExperimentalTime
fun initKoin(enableNetworkLogs: Boolean = false, appDeclaration: KoinAppDeclaration = {}) =
    startKoin {
        appDeclaration()
        modules(commonModule())
    }

// called by iOS etc
@ExperimentalTime
fun initKoin() = initKoin() {}

@ExperimentalTime
fun commonModule() = module {
    single { createJson() }
    single { createHttpClient(get()) }
    single { SPTransAPIRepository() }
    single { SPTransAPI(get()) }
}

fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

fun createHttpClient(json: Json) = HttpClient {
    install(ContentNegotiation) {
        json(json)
    }
    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.INFO
    }
}