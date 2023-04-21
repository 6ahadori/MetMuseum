package com.bahadori.metropolitanmuseum.core.network.retrofit

import com.bahadori.metropolitanmuseum.core.network.retrofit.Endpoints.GET_OBJECTS
import com.bahadori.metropolitanmuseum.core.network.retrofit.Endpoints.SEARCH
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.Element
import com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetApi {
    @GET(SEARCH)
    suspend fun search(
        @Query("q") query: String,
        @Query("isHighlight") isHighlight: Boolean?,
        @Query("title") title: Boolean?,
        @Query("tags") tags: Boolean?,
        @Query("departmentId") departmentId: Int?,
        @Query("isOnView") isOnView: Boolean?,
        @Query("artistOrCulture") artistOrCulture: Boolean?,
        @Query("medium") medium: String?,
        @Query("hasImages") hasImages: Boolean?,
        @Query("geoLocation") geoLocation: String?,
        @Query("dateBegin") dateBegin: Int?,
        @Query("dateEnd") dateEnd: Int?
    ): Response<SearchResponse>

    @GET(GET_OBJECTS)
    suspend fun getObject(@Path("objectID") objectID: Int): Element

}