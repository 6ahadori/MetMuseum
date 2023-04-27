package com.bahadori.database.model

import com.bahadori.model.Measurement


data class MeasurementEntity(
    val elementName: String?,
    val elementDescription: String?,
    val elementMeasurements: ElementMeasurementsEntity?
)

fun MeasurementEntity.asMeasurement() = com.bahadori.model.Measurement(
    elementName = elementName,
    elementDescription = elementDescription,
    elementMeasurements = elementMeasurements?.asElementMeasurements(),
)

fun List<MeasurementEntity>.asMeasurements() = map { entity ->
    entity.asMeasurement()
}