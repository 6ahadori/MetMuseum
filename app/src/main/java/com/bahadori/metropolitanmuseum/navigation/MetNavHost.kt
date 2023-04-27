package com.bahadori.metropolitanmuseum.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.bahadori.detail.navigation.detailScreen
import com.bahadori.detail.navigation.navigateToDetail
import com.bahadori.search.navigation.searchNavigationRoute
import com.bahadori.search.navigation.searchScreen

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
        detailScreen(onBackClicked = {
            navController.navigateUp()
        })
    }
}
