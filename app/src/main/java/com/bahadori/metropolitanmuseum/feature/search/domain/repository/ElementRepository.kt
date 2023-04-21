package com.bahadori.metropolitanmuseum.feature.search.domain.repository

import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.Element
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.SearchResponse

interface ElementRepository {

    suspend fun search(
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
    ): List<Int>

    suspend fun getElements(vararg objectID: Int): Result<List<Element>>

}