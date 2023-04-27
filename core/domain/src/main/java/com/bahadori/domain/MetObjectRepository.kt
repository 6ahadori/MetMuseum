package com.bahadori.domain

import com.bahadori.model.MetObject

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

    suspend fun getMetObjects(vararg objectID: Int): Result<List<com.bahadori.model.MetObject>>

    suspend fun getMetObject(objectID: Int): Result<com.bahadori.model.MetObject>

}