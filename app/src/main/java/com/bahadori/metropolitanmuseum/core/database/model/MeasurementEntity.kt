package com.bahadori.metropolitanmuseum.core.database.model

import com.bahadori.metropolitanmuseum.model.data.Measurement


data class MeasurementEntity(
    val elementName: String?,
    val elementDescription: String?,
    val elementMeasurements: ElementMeasurementsEntity?
)

fun MeasurementEntity.asMeasurement() = Measurement(
    elementName = elementName,
    elementDescription = elementDescription,
    elementMeasurements = elementMeasurements?.asElementMeasurements(),
)

fun List<MeasurementEntity>.asMeasurements() = map { entity ->
    entity.asMeasurement()
}