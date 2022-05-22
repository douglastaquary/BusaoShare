package com.douglastaquary.busaoshare.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArrivalOfVehiclesPerTrip(
    @SerialName("cl") val id: Int,
    @SerialName("hr") val updatedTime: String,
    @SerialName("lt") val stops: List<BusStop>?,
    var arrivalId: String = id.toString()
)