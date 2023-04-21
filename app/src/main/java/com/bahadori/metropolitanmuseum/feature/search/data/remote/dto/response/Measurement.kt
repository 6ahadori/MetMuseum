package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import androidx.annotation.Keep

@Keep
data class Measurement(
    val elementName: String?,
    val elementDescription: String?,
    val elementMeasurements: ElementMeasurements?
)