package com.bahadori.database.util.converter

import androidx.room.TypeConverter
import com.google.gson.Gson

class StringConverter {
    @TypeConverter
    fun toStrings(value: String): List<String>? {
        if (value.isBlank()) {
            return null
        }
        return Gson().fromJson(value, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun fromStrings(value: List<String>?): String {
        if (value == null) {
            return ""
        }
        return Gson().toJson(value)
    }
}