package com.bahadori.data.model


import com.bahadori.network.dto.response.TagDto


fun TagDto.asTagEntity() = com.bahadori.database.model.TagEntity(
    term = term,
    aatUrl = aatUrl,
    wikidataUrl = wikidataUrl,
)

fun List<TagDto>?.asTagEntities() = this?.map { entity ->
    entity.asTagEntity()
}