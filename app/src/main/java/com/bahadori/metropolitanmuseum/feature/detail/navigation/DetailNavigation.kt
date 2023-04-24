package com.bahadori.metropolitanmuseum.feature.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.bahadori.metropolitanmuseum.feature.detail.presentation.DetailRoute

const val detailNavigationRoute = "detail/{objectID}"

fun NavController.navigateToDetail(objectID: Int = -1, navOptions: NavOptions? = null) {
    this.navigate(detailNavigationRoute.replace("{objectID}", "$objectID"), navOptions)
}

fun NavGraphBuilder.detailScreen() {
    composable(
        route = detailNavigationRoute,
        arguments = listOf(navArgument("objectID") { type = NavType.IntType })
    ) { backStackEntry ->
        val objectID = backStackEntry.arguments?.getInt("objectID") ?: -1
        DetailRoute(objectID = objectID)
    }
}