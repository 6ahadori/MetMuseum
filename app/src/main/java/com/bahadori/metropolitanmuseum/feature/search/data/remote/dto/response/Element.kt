package com.bahadori.metropolitanmuseum.feature.search.data.remote.dto.response


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Element(
    val objectID: Int?,
    val isHighlight: Boolean?,
    val accessionNumber: String?,
    val accessionYear: String?,
    val isPublicDomain: Boolean?,
    val primaryImage: String?,
    val primaryImageSmall: String?,
    val additionalImages: List<Any?>?,
    val constituents: List<Constituent?>?,
    val department: String?,
    val objectName: String?,
    val title: String?,
    val culture: String?,
    val period: String?,
    val dynasty: String?,
    val reign: String?,
    val portfolio: String?,
    val artistRole: String?,
    val artistPrefix: String?,
    val artistDisplayName: String?,
    val artistDisplayBio: String?,
    val artistSuffix: String?,
    val artistAlphaSort: String?,
    val artistNationality: String?,
    val artistBeginDate: String?,
    val artistEndDate: String?,
    val artistGender: String?,
    @SerializedName("artistWikidata_URL")
    val artistWikidataURL: String?,
    @SerializedName("artistULAN_URL")
    val artistULANURL: String?,
    val objectDate: String?,
    val objectBeginDate: Int?,
    val objectEndDate: Int?,
    val medium: String?,
    val dimensions: String?,
    val measurements: List<Measurement?>?,
    val creditLine: String?,
    val geographyType: String?,
    val city: String?,
    val state: String?,
    val county: String?,
    val country: String?,
    val region: String?,
    val subregion: String?,
    val locale: String?,
    val locus: String?,
    val excavation: String?,
    val river: String?,
    val classification: String?,
    val rightsAndReproduction: String?,
    val linkResource: String?,
    val metadataDate: String?,
    val repository: String?,
    val objectURL: String?,
    val tags: List<Tag?>?,
    @SerializedName("objectWikidata_URL")
    val objectWikidataURL: String?,
    val isTimelineWork: Boolean?,
    @SerializedName("GalleryNumber")
    val galleryNumber: String?
)