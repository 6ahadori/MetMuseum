package com.bahadori.network.dto.response



data class MeasurementDto(
    val elementName: String?,
    val elementDescription: String?,
    val elementMeasurements: ElementMeasurementsDto?
)