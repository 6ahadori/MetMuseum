package com.bahadori.data.model


import com.bahadori.database.model.MetObjectEntity
import com.bahadori.network.dto.response.MetObjectDto


fun MetObjectDto.asMetObjectEntity() = com.bahadori.database.model.MetObjectEntity(
    objectID = objectID,
    isHighlight = isHighlight,
    accessionNumber = accessionNumber,
    accessionYear = accessionYear,
    primaryImage = primaryImage,
    additionalImages = additionalImages?.filterNotNull(),
    constituents = constituents?.filterNotNull().asConstituentEntities(),
    department = department,
    objectName = objectName,
    title = title,
    culture = culture,
    period = period,
    dynasty = dynasty,
    reign = reign,
    portfolio = portfolio,
    artistRole = artistRole,
    artistPrefix = artistPrefix,
    artistDisplayName = artistDisplayName,
    artistDisplayBio = artistDisplayBio,
    artistSuffix = artistSuffix,
    artistAlphaSort = artistAlphaSort,
    artistNationality = artistNationality,
    artistBeginDate = artistBeginDate,
    artistEndDate = artistEndDate,
    artistGender = artistGender,
    artistWikidataURL = artistWikidataURL,
    artistULANURL = artistULANURL,
    objectDate = objectDate,
    objectBeginDate = objectBeginDate,
    objectEndDate = objectEndDate,
    medium = medium,
    dimensions = dimensions,
    measurements = measurements?.filterNotNull().asMeasurementEntities(),
    creditLine = creditLine,
    geographyType = geographyType,
    city = city,
    state = state,
    county = county,
    country = country,
    region = region,
    subregion = subregion,
    locale = locale,
    locus = locus,
    excavation = excavation,
    river = river,
    classification = classification,
    rightsAndReproduction = rightsAndReproduction,
    linkResource = linkResource,
    metadataDate = metadataDate,
    repository = repository,
    objectURL = objectURL,
    tags = tags?.filterNotNull().asTagEntities(),
    objectWikidataUrl = objectWikidataUrl
)

fun List<MetObjectDto>.asMetObjectEntities() = this.map { entity ->
    entity.asMetObjectEntity()
}