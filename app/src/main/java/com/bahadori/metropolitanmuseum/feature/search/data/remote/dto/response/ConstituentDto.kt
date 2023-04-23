package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.bahadori.metropolitanmuseum.core.database.model.ConstituentEntity
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

fun ConstituentDto.asConstituentEntity() = ConstituentEntity(
    constituentID = constituentID,
    role = role,
    name = name,
    constituentULANURL = constituentULANURL,
    constituentWikidataURL = constituentWikidataURL,
    gender = gender,
)

fun List<ConstituentDto>?.asConstituentEntities() = this?.map { entity ->
    entity.asConstituentEntity()
}