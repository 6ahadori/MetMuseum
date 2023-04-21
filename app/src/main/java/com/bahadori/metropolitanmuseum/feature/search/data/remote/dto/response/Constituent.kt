package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Constituent(
    val constituentID: Int?,
    val role: String?,
    val name: String?,
    @SerializedName("constituentULAN_URL")
    val constituentULANURL: String?,
    @SerializedName("constituentWikidata_URL")
    val constituentWikidataURL: String?,
    val gender: String?
)