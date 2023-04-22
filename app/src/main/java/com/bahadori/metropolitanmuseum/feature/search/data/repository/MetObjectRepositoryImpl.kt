package com.bahadori.metropolitanmuseum.feature.search.data.repository

import com.bahadori.metropolitanmuseum.core.network.retrofit.MetApi
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.MetObject
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.MetObjectRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope

class MetObjectRepositoryImpl(
    private val api: MetApi,
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
        if (query.isNullOrBlank())
            return emptyList()

        val searchResponse = api.search(
            query, isHighlight, title, tags, departmentId, isOnView,
            artistOrCulture, medium, hasImages, geoLocation, dateBegin, dateEnd
        )

        return if (searchResponse.isSuccessful) {
            searchResponse.body()?.objectIDs?.filterNotNull() ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun getMetObjects(vararg objectID: Int): Result<List<MetObject>> {
        val objects = mutableListOf<Deferred<MetObject?>>()

        supervisorScope {
            objectID.forEach { id ->
                val `object` = async {
                    try {
                        api.getObject(id)
                    } catch (e: Throwable) {
                        null
                    }
                }
                objects.add(`object`)
            }
        }

        return Result.success(objects.awaitAll().filterNotNull())
    }
}