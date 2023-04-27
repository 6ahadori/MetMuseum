package com.bahadori.database.util.converter

import androidx.room.TypeConverter
import com.bahadori.database.model.TagEntity
import com.google.gson.Gson

class TagConverter {
    @TypeConverter
    fun toTags(value: String): List<TagEntity>? {
        if (value.isBlank()) {
            return null
        }
        return Gson().fromJson(value, Array<TagEntity>::class.java).toList()
    }

    @TypeConverter
    fun fromTags(value: List<TagEntity>?): String {
        if (value == null) {
            return ""
        }
        return Gson().toJson(value)
    }
}