package com.bahadori.metropolitanmuseum.feature.search.data.repository

import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.Element
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.SearchResponse
import com.bahadori.metropolitanmuseum.feature.search.data.repository.FakeData.objectsData
import com.bahadori.metropolitanmuseum.feature.search.domain.repository.ElementRepository
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
    ): List<Int> {
        val searchResponse = gson.fromJson(FakeData.searchData, SearchResponse::class.java)
        return searchResponse.objectIDs?.filterNotNull() ?: emptyList()
    }

    override suspend fun getElements(vararg objectID: Int): Result<List<Element>> {
        return Result.success(objectsData)
    }
}