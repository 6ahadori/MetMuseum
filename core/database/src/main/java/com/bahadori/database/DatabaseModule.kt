package com.bahadori.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Module for Database which provides MetDatabase
 * @see MetDatabase
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun providesMetDatabase(
        @ApplicationContext context: Context,
    ): MetDatabase = Room.databaseBuilder(
        context,
        MetDatabase::class.java,
        "met-database",
    ).build()
}
