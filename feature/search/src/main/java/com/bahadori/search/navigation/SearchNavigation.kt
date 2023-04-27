package com.bahadori.search.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.bahadori.search.SearchRoute

const val searchNavigationRoute = "search"

fun NavController.navigateToSearch(navOptions: NavOptions? = null) {
    this.navigate(searchNavigationRoute, navOptions)
}

fun NavGraphBuilder.searchScreen(onObjectClicked: (Int) -> Unit) {
    composable(route = searchNavigationRoute) {
        SearchRoute(onObjectClicked)
    }
}