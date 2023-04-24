package com.bahadori.metropolitanmuseum.feature.search.data.repository

import com.bahadori.metropolitanmuseum.core.database.dao.MetObjectDao
import com.bahadori.metropolitanmuseum.core.database.model.asMetObject
import com.bahadori.metropolitanmuseum.core.network.retrofit.MetApi
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.asMetObjectEntity
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.MetObjectRepository
import com.bahadori.metropolitanmuseum.model.data.MetObject
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

class MetObjectRepositoryImpl(
    private val api: MetApi, private val dao: MetObjectDao
) : MetObjectRepository {

    override suspend fun search(
        query: String?,
        isHighlight: Boolean?,
        title: Boolean?,
        tags: Boolean?,
        departmentId: Int?,
        isOnView: Boolean?,
        artistOrCulture: Boolean?,
        medium: String?,
        hasImages: Boolean?,
        geoLocation: String?,
        dateBegin: Int?,
        dateEnd: Int?
    ): List<Int> {
        if (query.isNullOrBlank()) return emptyList()

        return try {
            val searchResponse = api.search(
                query,
                isHighlight,
                title,
                tags,
                departmentId,
                isOnView,
                artistOrCulture,
                medium,
                hasImages,
                geoLocation,
                dateBegin,
                dateEnd
            )

            return if (searchResponse.isSuccessful) {
                searchResponse.body()?.objectIDs?.filterNotNull() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun getMetObjects(vararg objectID: Int): Result<List<MetObject>> {
        val objects = mutableListOf<Deferred<MetObject?>>()

        supervisorScope {
            objectID.forEach { id ->
                val `object` = async {
                    try {
                        val cacheObject = dao.getObject(id)
                        if (cacheObject == null) {
                            val networkObject = api.getObject(id).asMetObjectEntity()
                            dao.insertObject(networkObject)
                            networkObject.asMetObject()
                        } else {
                            cacheObject.asMetObject()
                        }
                    } catch (e: Throwable) {
                        null
                    }
                }
                objects.add(`object`)
            }
        }

        return Result.success(objects.awaitAll().filterNotNull())
    }

    override suspend fun getMetObject(objectID: Int): Result<MetObject> {
        return try {
            val cacheObject = dao.getObject(objectID)
            if (cacheObject == null) {
                val networkObject = api.getObject(objectID).asMetObjectEntity()
                dao.insertObject(networkObject)
                Result.success(networkObject.asMetObject())
            } else {
                Result.success(cacheObject.asMetObject())
            }
        } catch (e: Throwable) {
            Result.failure(e)
        }
    }
}