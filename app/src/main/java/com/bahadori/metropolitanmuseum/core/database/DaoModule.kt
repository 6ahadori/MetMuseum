package com.bahadori.metropolitanmuseum.core.database

import com.bahadori.metropolitanmuseum.core.database.dao.MetObjectDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun providesMetObjectDao(
        database: MetDatabase,
    ): MetObjectDao = database.objectDao()

}
