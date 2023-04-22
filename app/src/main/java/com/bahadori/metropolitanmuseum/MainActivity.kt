package com.bahadori.metropolitanmuseum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.bahadori.metropolitanmuseum.core.designsystem.theme.MetTheme
import com.bahadori.metropolitanmuseum.ui.MetApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalMaterial3WindowSizeClassApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        // Turn off the decor fitting system windows, which allows us to handle insets,
        // including IME animations
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MetTheme {
                MetApp(
                    windowSizeClass = calculateWindowSizeClass(this),
                )
            }
        }
    }
}