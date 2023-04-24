package com.bahadori.metropolitanmuseum.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bahadori.metropolitanmuseum.feature.detail.navigation.detailScreen
import com.bahadori.metropolitanmuseum.feature.detail.navigation.navigateToDetail
import com.bahadori.metropolitanmuseum.feature.search.navigation.searchNavigationRoute
import com.bahadori.metropolitanmuseum.feature.search.navigation.searchScreen

@Composable
fun MetNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = searchNavigationRoute,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
    ) {
        searchScreen(
            onObjectClicked = { id ->
                navController.navigateToDetail(id)
            }
        )
        detailScreen()
    }
}
