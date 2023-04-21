package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ElementMeasurements(
    @SerializedName("Height")
    val height: Double?,
    @SerializedName("Width")
    val width: Double?
)