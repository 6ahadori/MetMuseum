package com.bahadori.metropolitanmuseum.feature.search.data.repository

import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.Element
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.SearchResponse
import com.google.gson.Gson

class FakeElementRepository : ElementRepository {

    private val gson = Gson()

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
    ): SearchResponse {
        return gson.fromJson(FakeData.searchData, SearchResponse::class.java)
    }

    override suspend fun getElement(objectID: Int?): Element? {
        return gson.fromJson(FakeData.objData, Element::class.java)
    }
}