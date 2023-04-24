package com.bahadori.metropolitanmuseum.feature.detail.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bahadori.metropolitanmuseum.core.designsystem.component.DynamicAsyncImage
import com.bahadori.metropolitanmuseum.core.designsystem.component.StatusBarColor
import com.bahadori.metropolitanmuseum.core.designsystem.component.fadeEdge
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun DetailRoute(
    objectID: Int = -1,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    DetailScreen(
        state = state.value
    )

}

@Composable
fun DetailScreen(
    state: DetailContract.State,
) {

    StatusBarColor(darkIcons = false)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        DynamicAsyncImage(
            imageUrl = state.currentObject?.primaryImage ?: "",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .fadeEdge(
                    colors = listOf(Color.Black, Color.Transparent, Color.Transparent, Color.Black)
                )
        )
    }
}