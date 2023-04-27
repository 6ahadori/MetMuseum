package com.bahadori.database

import com.bahadori.database.dao.MetObjectDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


/**
 * Module for DAO which provides MetObjectDao to access database methods
 */
@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesMetObjectDao(
        database: MetDatabase,
    ): MetObjectDao = database.objectDao()

}
