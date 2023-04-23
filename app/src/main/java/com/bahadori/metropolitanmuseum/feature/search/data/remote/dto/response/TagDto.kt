package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.bahadori.metropolitanmuseum.core.database.model.TagEntity
import com.google.gson.annotations.SerializedName

data class TagDto(
    val term: String?,
    @SerializedName("AAT_URL") val aatUrl: String?,
    @SerializedName("Wikidata_URL") val wikidataUrl: String?
)

fun TagDto.asTagEntity() = TagEntity(
    term = term,
    aatUrl = aatUrl,
    wikidataUrl = wikidataUrl,
)

fun List<TagDto>?.asTagEntities() = this?.map { entity ->
    entity.asTagEntity()
}