package com.bahadori.database.model

import com.bahadori.model.Tag


data class TagEntity(
    val term: String?,
    val aatUrl: String?,
    val wikidataUrl: String?
)

fun TagEntity.asTag() = com.bahadori.model.Tag(
    term = term,
    aatUrl = aatUrl,
    wikidataUrl = wikidataUrl,
)

fun List<TagEntity>?.asTags() = this?.map { entity ->
    entity.asTag()
}