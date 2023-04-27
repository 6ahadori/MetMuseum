package com.bahadori.data.model

import com.bahadori.network.dto.response.MeasurementDto


fun MeasurementDto.asMeasurementEntity() = com.bahadori.database.model.MeasurementEntity(
    elementName = elementName,
    elementDescription = elementDescription,
    elementMeasurements = elementMeasurements?.asElementMeasurementsEntity(),
)

fun List<MeasurementDto>?.asMeasurementEntities() = this?.map { entity ->
    entity.asMeasurementEntity()
}