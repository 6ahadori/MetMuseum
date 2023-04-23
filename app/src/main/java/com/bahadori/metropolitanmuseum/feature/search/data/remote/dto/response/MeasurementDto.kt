package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.bahadori.metropolitanmuseum.core.database.model.MeasurementEntity

data class MeasurementDto(
    val elementName: String?,
    val elementDescription: String?,
    val elementMeasurements: ElementMeasurementsDto?
)

fun MeasurementDto.asMeasurementEntity() = MeasurementEntity(
    elementName = elementName,
    elementDescription = elementDescription,
    elementMeasurements = elementMeasurements?.asElementMeasurementsEntity(),
)

fun List<MeasurementDto>?.asMeasurementEntities() = this?.map { entity ->
    entity.asMeasurementEntity()
}