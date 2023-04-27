package com.bahadori.data.di

import com.bahadori.data.repository.MetObjectRepositoryImpl
import com.bahadori.domain.MetObjectRepository
import com.bahadori.network.retrofit.MetApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ObjectModule {

    @Provides
    @Singleton
    fun provideElementRepository(
        api: MetApi,
        dao: com.bahadori.database.dao.MetObjectDao
    ): MetObjectRepository {
        return MetObjectRepositoryImpl(api, dao)
    }

}