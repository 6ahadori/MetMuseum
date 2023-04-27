package com.bahadori.database.model


data class ConstituentEntity(
    val constituentID: Int?,
    val role: String?,
    val name: String?,
    val constituentULANURL: String?,
    val constituentWikidataURL: String?,
    val gender: String?
)

fun List<ConstituentEntity>.asConstituents() = map { entity ->
    com.bahadori.model.Constituent(
        constituentID = entity.constituentID,
        role = entity.role,
        name = entity.name,
        constituentULANURL = entity.constituentULANURL,
        constituentWikidataURL = entity.constituentWikidataURL,
        gender = entity.gender,
    )
}

