package com.bahadori.database.dao.di

import android.content.Context
import androidx.room.Room
import com.bahadori.database.DaoModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Named

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DaoModule::class]
)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDatabase(@ApplicationContext context: Context): com.bahadori.database.MetDatabase {
        return Room.inMemoryDatabaseBuilder(context, com.bahadori.database.MetDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }
}