package com.bahadori.database.util.converter

import androidx.room.TypeConverter
import com.bahadori.database.model.ConstituentEntity
import com.google.gson.Gson

class ConstituentConverter {
    @TypeConverter
    fun toConstituents(value: String): List<ConstituentEntity>? {
        if (value.isBlank()) {
            return null
        }
        return Gson().fromJson(value, Array<ConstituentEntity>::class.java).toList()
    }

    @TypeConverter
    fun fromConstituents(value: List<ConstituentEntity>?): String {
        if (value == null) {
            return ""
        }
        return Gson().toJson(value)
    }
}