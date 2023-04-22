package com.bahadori.metropolitanmuseum.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bahadori.metropolitanmuseum.core.designsystem.navigation.Destinations
import com.bahadori.metropolitanmuseum.feature.search.navigation.searchNavigationRoute
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMetAppState(
    windowSizeClass: WindowSizeClass,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController(),
): MetAppState {
    return remember(
        navController,
        coroutineScope,
        windowSizeClass,
    ) {
        MetAppState(
            navController,
            coroutineScope,
            windowSizeClass,
        )
    }
}

class MetAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val destinations: List<Destinations> = Destinations.values()

    val currentMetDestination: Destinations?
        @Composable get() = when (currentDestination?.route) {
            searchNavigationRoute -> Destinations.SearchScreen
            else -> null
        }
}