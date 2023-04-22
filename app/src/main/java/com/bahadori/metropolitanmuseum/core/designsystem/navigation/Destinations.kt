package com.bahadori.metropolitanmuseum.core.designsystem.navigation

import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.feature.search.navigation.searchNavigationRoute

sealed class Destinations(val route: String, val titleTextId: Int) {
    object SearchScreen : Destinations(searchNavigationRoute, R.string.search_screen)
    object DetailScreen : Destinations("detail_screen", R.string.detail_screen)

    companion object {
        fun values(): List<Destinations> {
            return listOf(
                SearchScreen, DetailScreen
            )
        }
    }
}