package com.bahadori.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bahadori.database.dao.MetObjectDao
import com.bahadori.database.model.MetObjectEntity
import com.bahadori.database.util.converter.ConstituentConverter
import com.bahadori.database.util.converter.MeasurementConverter
import com.bahadori.database.util.converter.StringConverter
import com.bahadori.database.util.converter.TagConverter

@Database(
    entities = [
        MetObjectEntity::class
    ],
    version = 1,
    exportSchema = false,
)
@TypeConverters(
    ConstituentConverter::class,
    MeasurementConverter::class,
    StringConverter::class,
    TagConverter::class,
)
abstract class MetDatabase : RoomDatabase() {
    abstract fun objectDao(): MetObjectDao
}
