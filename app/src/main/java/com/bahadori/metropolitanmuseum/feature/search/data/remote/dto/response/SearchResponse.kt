package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import androidx.annotation.Keep

@Keep
data class SearchResponse(
    val total: Int?,
    val objectIDs: List<Int?>?
)