package com.bahadori.data.model


import com.bahadori.database.model.ElementMeasurementsEntity
import com.bahadori.network.dto.response.ElementMeasurementsDto

fun ElementMeasurementsDto.asElementMeasurementsEntity() =
    com.bahadori.database.model.ElementMeasurementsEntity(
        height = height,
        width = width,
        depth = depth
    )