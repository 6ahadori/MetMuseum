package com.bahadori.metropolitanmuseum.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.bahadori.metropolitanmuseum.R
import com.bahadori.designsystem.component.MetBackground
import com.bahadori.designsystem.component.MetTopAppBar
import com.bahadori.metropolitanmuseum.navigation.Destinations
import com.bahadori.metropolitanmuseum.core.designsystem.theme.gold
import com.bahadori.metropolitanmuseum.navigation.MetNavHost

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalLayoutApi::class,
    ExperimentalComposeUiApi::class,
)
@Composable
fun MetApp(
    windowSizeClass: WindowSizeClass,
    appState: MetAppState = rememberMetAppState(
        windowSizeClass = windowSizeClass,
    ),
) {

    MetBackground {
        val snackbarHostState = remember { SnackbarHostState() }
        Scaffold(
            modifier = Modifier
                .semantics {
                    testTagsAsResourceId = true
                },
            contentColor = MaterialTheme.colorScheme.onBackground,
            snackbarHost = { SnackbarHost(snackbarHostState) },
            contentWindowInsets = WindowInsets(
                top = 0,
                bottom = WindowInsets.systemBars.getBottom(LocalDensity.current)
            ),
        ) { padding ->
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .consumeWindowInsets(padding)
                    .windowInsetsPadding(
                        WindowInsets.safeDrawing.only(
                            WindowInsetsSides.Horizontal,
                        ),
                    ),
            ) {
                val destination = appState.currentMetDestination
                if (destination != null && destination.route == Destinations.SearchScreen.route)
                    MetTopAppBar(
                        title = {
                            val titleString = buildAnnotatedString {
                                append(stringResource(id = R.string.metropolitan))
                                withStyle(style = SpanStyle(gold)) {
                                    append(stringResource(id = R.string.museum))
                                }
                            }
                            Text(
                                modifier = Modifier.padding(horizontal = 12.dp),
                                text = titleString,
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontFamily = FontFamily.SansSerif,
                                    fontWeight = FontWeight.Black
                                ),
                            )
                        },
                        colors = TopAppBarDefaults.smallTopAppBarColors(
                            containerColor = Color.Transparent,
                        ),
                    )

                MetNavHost(appState.navController)
            }

            // TODO: We may want to add padding or spacer when the snackbar is shown so that
            //  content doesn't display behind it.
        }
    }
}