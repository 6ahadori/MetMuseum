package com.bahadori.metropolitanmuseum.feature.search.domain.repository

import com.bahadori.metropolitanmuseum.model.data.MetObject

interface MetObjectRepository {

    suspend fun search(
        query: String?,
        isHighlight: Boolean? = null,
        title: Boolean? = null,
        tags: Boolean? = null,
        departmentId: Int? = null,
        isOnView: Boolean? = null,
        artistOrCulture: Boolean? = null,
        medium: String? = null,
        hasImages: Boolean? = null,
        geoLocation: String? = null,
        dateBegin: Int? = null,
        dateEnd: Int? = null
    ): List<Int>

    suspend fun getMetObjects(vararg objectID: Int): Result<List<MetObject>>

    suspend fun getMetObject(objectID: Int): Result<MetObject>

}