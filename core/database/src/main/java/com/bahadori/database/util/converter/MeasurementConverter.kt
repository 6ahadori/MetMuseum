package com.bahadori.database.util.converter

import androidx.room.TypeConverter
import com.bahadori.database.model.MeasurementEntity
import com.google.gson.Gson

class MeasurementConverter {
    @TypeConverter
    fun toMeasurements(value: String): List<MeasurementEntity>? {
        if (value.isBlank()) {
            return null
        }
        return Gson().fromJson(value, Array<MeasurementEntity>::class.java).toList()
    }

    @TypeConverter
    fun fromMeasurements(value: List<MeasurementEntity>?): String {
        if (value == null) {
            return ""
        }
        return Gson().toJson(value)
    }
}