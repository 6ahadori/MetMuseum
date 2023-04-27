package com.bahadori.detail

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bahadori.designsystem.component.DynamicAsyncImage
import com.bahadori.designsystem.component.StatusBarColor
import com.bahadori.designsystem.component.fadeEdge
import com.bahadori.designsystem.icon.MetIcons
import com.bahadori.detail.DetailContract.*
import com.bahadori.detail.component.GalleyView
import com.bahadori.detail.component.MetObjectDetailView
import com.bahadori.detail.component.NoObject


@Composable
fun DetailRoute(
    objectID: Int = -1,
    onBackClicked: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle()

    DetailScreen(
        state = state.value,
        onBackClicked = onBackClicked,
        onGalleryClicked = {
            viewModel.event(Event.ShowGallery)
        }
    )

}

@Composable
fun DetailScreen(
    state: State,
    onGalleryClicked: () -> Unit,
    onBackClicked: () -> Unit
) {

    StatusBarColor(darkIcons = false)

    BackHandler(state.showGallery) {
        onGalleryClicked()
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        DynamicAsyncImage(
            imageUrl = state.currentObject?.primaryImage ?: "",
            contentDescription = state.currentObject?.objectName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .matchParentSize()
                .fadeEdge(
                    colors = listOf(Color.Black, Color.Transparent, Color.Transparent, Color.Black)
                )
        )

        OutlinedIconButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp, 48.dp), onClick = onBackClicked,
            border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.5f)),
            colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = Color.White)
        ) {
            Icon(
                modifier = Modifier.size(16.dp),
                painter = painterResource(id = MetIcons.Chevron),
                contentDescription = "BackButton"
            )
        }

        state.currentObject?.let {
            if (state.images.isNotEmpty())
                OutlinedIconButton(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(16.dp, 48.dp),
                    border = BorderStroke(0.5.dp, Color.White.copy(alpha = 0.5f)),
                    onClick = onGalleryClicked,
                    colors = IconButtonDefaults.outlinedIconButtonColors(contentColor = Color.White)
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = MetIcons.Camera),
                        contentDescription = "Camera"
                    )
                }

            MetObjectDetailView(
                modifier = Modifier.align(Alignment.BottomCenter),
                metObject = state.currentObject
            )

            AnimatedVisibility(
                visible = state.showGallery,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                GalleyView(
                    modifier = Modifier,
                    images = state.images
                )
            }

        } ?: NoObject(modifier = Modifier.align(Alignment.BottomCenter))
    }
}