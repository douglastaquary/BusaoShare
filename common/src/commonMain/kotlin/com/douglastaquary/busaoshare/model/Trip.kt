package com.douglastaquary.busaoshare.model

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