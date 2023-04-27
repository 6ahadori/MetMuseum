package com.bahadori.data.model

import com.bahadori.network.dto.response.ConstituentDto


fun ConstituentDto.asConstituentEntity() = com.bahadori.database.model.ConstituentEntity(
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