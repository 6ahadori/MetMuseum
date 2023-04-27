package com.bahadori.database.model

import com.bahadori.model.ElementMeasurements


data class ElementMeasurementsEntity(
    val height: Double? = null,
    val width: Double? = null,
    val depth: Double? = null
)

fun ElementMeasurementsEntity.asElementMeasurements() = com.bahadori.model.ElementMeasurements(
    height = height,
    width = width,
    depth = depth
)