package com.bahadori.metropolitanmuseum.core.database.dao

import com.bahadori.metropolitanmuseum.core.database.model.ConstituentEntity
import com.bahadori.metropolitanmuseum.core.database.model.ElementMeasurementsEntity
import com.bahadori.metropolitanmuseum.core.database.model.MeasurementEntity
import com.bahadori.metropolitanmuseum.core.database.model.MetObjectEntity
import com.bahadori.metropolitanmuseum.core.database.model.TagEntity

object FakeData {
    val metObject = MetObjectEntity(
        objectID = 436101,
        isHighlight = true,
        accessionNumber = "49.7.21",
        accessionYear = "1949",
        primaryImage = "https://images.metmuseum.org/CRDImages/ep/original/DP-14936-023.jpg",
        additionalImages = listOf(
            "https://images.metmuseum.org/CRDImages/ep/original/LC-49_7_21-IRR.jpg",
            "https://images.metmuseum.org/CRDImages/ep/original/LC-49_7_21_Xray.jpg"
        ),
        constituents = listOf(
            ConstituentEntity(
                constituentID = 161810,
                role = "Artist",
                name = "Gerard David",
                constituentULANURL = "http://vocab.getty.edu/page/ulan/500025998",
                constituentWikidataURL = "https://www.wikidata.org/wiki/Q333380",
                gender = ""
            )
        ),
        department = "European Paintings",
        objectName = "Painting",
        title = "The Rest on the Flight into Egypt",
        culture = "",
        period = "",
        dynasty = "",
        reign = "",
        portfolio = "",
        artistRole = "Artist",
        artistPrefix = "",
        artistDisplayName = "Gerard David",
        artistDisplayBio = "Netherlandish, Oudewater ca. 1455–1523 Bruges",
        artistSuffix = "",
        artistAlphaSort = "David, Gerard",
        artistNationality = "Netherlandish",
        artistBeginDate = "1455",
        artistEndDate = "1523",
        artistGender = "",
        artistWikidataURL = "https://www.wikidata.org/wiki/Q333380",
        artistULANURL = "http://vocab.getty.edu/page/ulan/500025998",
        objectDate = "ca. 1512–15",
        objectBeginDate = 1512,
        objectEndDate = 1515,
        medium = "Oil on wood",
        dimensions = "21 in. × 15 11/16 in. (53.3 × 39.8 cm)",
        measurements = listOf(
            MeasurementEntity(
                elementName = "Overall",
                elementDescription = null,
                elementMeasurements = ElementMeasurementsEntity(
                    height = 53.340107,
                    width = 39.84633,
                    depth = null
                )
            ),
            MeasurementEntity(
                elementName = "Frame",
                elementDescription = null,
                elementMeasurements = ElementMeasurementsEntity(
                    depth = 6.350013,
                    height = 70.80264,
                    width = 57.467613
                )
            )
        ),
        creditLine = "The Jules Bache Collection, 1949",
        geographyType = "",
        city = "",
        state = "",
        county = "",
        country = "",
        region = "",
        subregion = "",
        locale = "",
        locus = "",
        excavation = "",
        river = "",
        classification = "Paintings",
        rightsAndReproduction = "",
        linkResource = "",
        metadataDate = "2023-04-18T04:46:43.39Z",
        repository = "Metropolitan Museum of Art, New York, NY",
        objectURL = "https://www.metmuseum.org/art/collection/search/436101",
        tags = listOf(
            TagEntity(
                term = "Virgin Mary",
                aatUrl = "http://vocab.getty.edu/page/ia/901000032",
                wikidataUrl = "https://www.wikidata.org/wiki/Q345"
            ),
            TagEntity(
                term = "Jesus",
                aatUrl = "http://vocab.getty.edu/page/ia/901000087",
                wikidataUrl = "https://www.wikidata.org/wiki/Q302"
            ),
            TagEntity(
                term = "Nursing",
                aatUrl = "http://vocab.getty.edu/page/aat/300379905",
                wikidataUrl = "https://www.wikidata.org/wiki/Q174876"
            )
        ),
        objectWikidataUrl = "https://www.wikidata.org/wiki/Q19911632",
    )
}