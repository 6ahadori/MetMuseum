package com.bahadori.metropolitanmuseum.core.database.model

import com.bahadori.metropolitanmuseum.model.data.Tag


data class TagEntity(
    val term: String?,
    val aatUrl: String?,
    val wikidataUrl: String?
)

fun TagEntity.asTag() = Tag(
    term = term,
    aatUrl = aatUrl,
    wikidataUrl = wikidataUrl,
)

fun List<TagEntity>?.asTags() = this?.map { entity ->
    entity.asTag()
}