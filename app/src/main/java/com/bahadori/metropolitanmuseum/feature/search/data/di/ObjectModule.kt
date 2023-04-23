package com.bahadori.metropolitanmuseum.feature.search.data.di

import com.bahadori.metropolitanmuseum.core.database.dao.MetObjectDao
import com.bahadori.metropolitanmuseum.core.network.retrofit.MetApi
import com.bahadori.metropolitanmuseum.feature.search.data.repository.MetObjectRepositoryImpl
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.MetObjectRepository
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
        dao: MetObjectDao
    ): MetObjectRepository {
        return MetObjectRepositoryImpl(api, dao)
    }

}