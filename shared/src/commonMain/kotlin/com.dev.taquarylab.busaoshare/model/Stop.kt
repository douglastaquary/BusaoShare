package com.dev.taquarylab.busaoshare.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stop(
    @SerialName("cp") val id: Int,
    @SerialName("np") val stopName: String,
    @SerialName("ed") val stopLocationAddress: String,
    @SerialName("py") val latitude: Double? = 0.0,
    @SerialName("px") val longitude: Double? = 0.0
)
