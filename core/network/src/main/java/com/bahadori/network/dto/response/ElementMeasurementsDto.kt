package com.bahadori.network.dto.response


import com.google.gson.annotations.SerializedName

data class ElementMeasurementsDto(
    @SerializedName("Height")
    val height: Double?,
    @SerializedName("Width")
    val width: Double?,
    @SerializedName("Depth")
    val depth: Double?
)