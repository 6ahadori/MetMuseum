package com.bahadori.data.repository

import com.bahadori.database.dao.MetObjectDao
import com.bahadori.database.model.asMetObject
import com.bahadori.data.model.asMetObjectEntity
import com.bahadori.domain.MetObjectRepository
import com.bahadori.network.retrofit.MetApi
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

    override suspend fun getMetObjects(vararg objectID: Int): Result<List<com.bahadori.model.MetObject>> {
        val objects = mutableListOf<Deferred<com.bahadori.model.MetObject?>>()

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

    override suspend fun getMetObject(objectID: Int): Result<com.bahadori.model.MetObject> {
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