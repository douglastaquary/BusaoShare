package com.dev.taquarylab.busaoshare.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BusStop(
    @SerialName("cp") val id: Int,
    @SerialName("np") val descript: String,
    @SerialName("py") val latitude: Double? = 0.0,
    @SerialName("px") val longitude: Double? = 0.0,
    @SerialName("vs") val vehicles: List<Vehicle>
)

@Serializable
data class Vehicle(
    @SerialName("p") val vehiclePrefix: String,
    @SerialName("t") val arrivalForecast: String?,
    @SerialName("a") val hasAccessibility: Boolean,
    @SerialName("ta") val lastUpdatedTime: String?,
    @SerialName("py") val latitude: Double? = 0.0,
    @SerialName("px") val longitude: Double? = 0.0,
)
