package com.bahadori.metropolitanmuseum.navigation

import com.bahadori.detail.navigation.detailNavigationRoute
import com.bahadori.search.navigation.searchNavigationRoute
import com.bahadori.designsystem.R

/**
 * Destinations of the app
 * @see SearchScreen is for search
 * @see Destinations is for detail
 */
sealed class Destinations(val route: String, val titleTextId: Int) {
    object SearchScreen : Destinations(com.bahadori.search.navigation.searchNavigationRoute, R.string.metropolitan_museum)
    object DetailScreen : Destinations(com.bahadori.detail.navigation.detailNavigationRoute, R.string.detail_screen)

    companion object {
        fun values(): List<Destinations> {
            return listOf(
                SearchScreen, DetailScreen
            )
        }
    }
}