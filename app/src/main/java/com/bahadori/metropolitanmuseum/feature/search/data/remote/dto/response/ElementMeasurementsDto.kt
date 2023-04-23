package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.bahadori.metropolitanmuseum.core.database.model.ElementMeasurementsEntity
import com.google.gson.annotations.SerializedName

data class ElementMeasurementsDto(
    @SerializedName("Height")
    val height: Double?,
    @SerializedName("Width")
    val width: Double?,
    @SerializedName("Depth")
    val depth: Double?
)

fun ElementMeasurementsDto.asElementMeasurementsEntity() = ElementMeasurementsEntity(
    height = height,
    width = width,
    depth = depth
)