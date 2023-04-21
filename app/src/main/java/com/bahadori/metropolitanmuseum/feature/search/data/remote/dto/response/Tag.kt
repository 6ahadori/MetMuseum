package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Tag(
    val term: String?,
    @SerializedName("AAT_URL") val aatUrl: String?,
    @SerializedName("Wikidata_URL") val wikidataUrl: String?
)