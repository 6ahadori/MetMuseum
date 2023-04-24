package com.bahadori.metropolitanmuseum.core.designsystem.navigation

import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.feature.detail.navigation.detailNavigationRoute
import com.bahadori.metropolitanmuseum.feature.search.navigation.searchNavigationRoute

sealed class Destinations(val route: String, val titleTextId: Int) {
    object SearchScreen : Destinations(searchNavigationRoute, R.string.search_screen)
    object DetailScreen : Destinations(detailNavigationRoute, R.string.detail_screen)

    companion object {
        fun values(): List<Destinations> {
            return listOf(
                SearchScreen, DetailScreen
            )
        }
    }
}