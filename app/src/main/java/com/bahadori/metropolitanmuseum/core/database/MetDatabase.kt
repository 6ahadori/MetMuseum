package com.bahadori.metropolitanmuseum.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bahadori.metropolitanmuseum.core.database.dao.MetObjectDao
import com.bahadori.metropolitanmuseum.core.database.model.MetObjectEntity
import com.bahadori.metropolitanmuseum.core.database.util.converter.ConstituentConverter
import com.bahadori.metropolitanmuseum.core.database.util.converter.MeasurementConverter
import com.bahadori.metropolitanmuseum.core.database.util.converter.StringConverter
import com.bahadori.metropolitanmuseum.core.database.util.converter.TagConverter

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
