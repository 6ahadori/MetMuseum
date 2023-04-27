package com.bahadori.network.retrofit

import com.bahadori.network.dto.response.MetObjectDto
import com.bahadori.network.dto.response.SearchResponse
import com.bahadori.network.retrofit.Endpoints.GET_OBJECTS
import com.bahadori.network.retrofit.Endpoints.SEARCH
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MetApi {
    @GET(SEARCH)
    suspend fun search(
        @Query("q") query: String,
        @Query("isHighlight") isHighlight: Boolean? = null,
        @Query("title") title: Boolean? = null,
        @Query("tags") tags: Boolean? = null,
        @Query("departmentId") departmentId: Int? = null,
        @Query("isOnView") isOnView: Boolean? = null,
        @Query("artistOrCulture") artistOrCulture: Boolean? = null,
        @Query("medium") medium: String? = null,
        @Query("hasImages") hasImages: Boolean? = null,
        @Query("geoLocation") geoLocation: String? = null,
        @Query("dateBegin") dateBegin: Int? = null,
        @Query("dateEnd") dateEnd: Int? = null
    ): Response<SearchResponse>

    @GET(GET_OBJECTS)
    suspend fun getObject(@Path("objectID") objectID: Int): MetObjectDto

}