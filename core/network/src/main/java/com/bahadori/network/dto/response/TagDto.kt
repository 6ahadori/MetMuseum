package com.bahadori.network.dto.response


import com.google.gson.annotations.SerializedName

data class TagDto(
    val term: String?,
    @SerializedName("AAT_URL") val aatUrl: String?,
    @SerializedName("Wikidata_URL") val wikidataUrl: String?
)