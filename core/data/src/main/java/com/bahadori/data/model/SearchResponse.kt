package com.bahadori.data.model


import androidx.annotation.Keep

data class SearchResponse(
    val total: Int?,
    val objectIDs: List<Int?>?
)