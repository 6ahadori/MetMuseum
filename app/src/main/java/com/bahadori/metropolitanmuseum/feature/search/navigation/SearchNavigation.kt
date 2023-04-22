package com.bahadori.metropolitanmuseum.feature.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bahadori.metropolitanmuseum.feature.search.presentation.SearchRoute

const val searchNavigationRoute = "search"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen() {
    composable(route = searchNavigationRoute) {
        SearchRoute()
    }
}