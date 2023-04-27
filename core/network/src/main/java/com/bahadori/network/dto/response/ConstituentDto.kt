package com.bahadori.network.dto.response


import com.google.gson.annotations.SerializedName

data class ConstituentDto(
    val constituentID: Int?,
    val role: String?,
    val name: String?,
    @SerializedName("constituentULAN_URL")
    val constituentULANURL: String?,
    @SerializedName("constituentWikidata_URL")
    val constituentWikidataURL: String?,
    val gender: String?
)