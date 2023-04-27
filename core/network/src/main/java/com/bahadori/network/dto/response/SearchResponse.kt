package com.bahadori.network.dto.response


import androidx.annotation.Keep

@Keep
data class SearchResponse(
    val total: Int?,
    val objectIDs: List<Int?>?
)