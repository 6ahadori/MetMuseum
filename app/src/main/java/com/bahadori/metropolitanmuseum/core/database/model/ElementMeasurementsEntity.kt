package com.bahadori.metropolitanmuseum.core.database.model

import com.bahadori.metropolitanmuseum.model.data.ElementMeasurements


data class ElementMeasurementsEntity(
    val height: Double? = null,
    val width: Double? = null,
    val depth: Double? = null
)

fun ElementMeasurementsEntity.asElementMeasurements() = ElementMeasurements(
    height = height,
    width = width,
    depth = depth
)