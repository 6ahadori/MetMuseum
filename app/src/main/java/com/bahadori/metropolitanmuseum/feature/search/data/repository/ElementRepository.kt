package com.bahadori.metropolitanmuseum.feature.search.data.repository

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
    ): SearchResponse

    suspend fun getElement(objectID: Int?): Element?
}