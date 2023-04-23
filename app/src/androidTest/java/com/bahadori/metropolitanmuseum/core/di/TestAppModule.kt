package com.bahadori.metropolitanmuseum.core.di

import android.content.Context
import androidx.room.Room
import com.bahadori.metropolitanmuseum.core.database.MetDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDatabase(@ApplicationContext context: Context): MetDatabase {
        return Room.inMemoryDatabaseBuilder(context, MetDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}