@file:OptIn(ExperimentalMaterial3Api::class)

package com.bahadori.metropolitanmuseum.feature.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.common.loading.justLoading
import com.bahadori.metropolitanmuseum.core.designsystem.component.LoadingView
import com.bahadori.metropolitanmuseum.core.designsystem.component.MetTextField
import com.bahadori.metropolitanmuseum.feature.search.presentation.SearchContract.*
import com.bahadori.metropolitanmuseum.feature.search.presentation.component.MetObjectView

@Composable
internal fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
) {

    val state = viewModel.state.collectAsStateWithLifecycle()

    SearchScreen(
        state = state.value,
        onQueryChanged = {
            viewModel.event(Event.OnQueryEntered(it))
        },
        onLoadObjects = {
            viewModel.event(Event.OnLoadMore)
        }
    )

}

@Composable
internal fun SearchScreen(
    state: State,
    onQueryChanged: (String) -> Unit,
    onLoadObjects: () -> Unit
) {

    val gridState = rememberLazyGridState()

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            MetTextField(
                label = R.string.search,
                value = state.query,
                onValueChange = onQueryChanged
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .fillMaxSize()
                    .testTag("search"),
                state = gridState,
            ) {
                items(state.objects.size) { index ->
                    val item = state.objects[index]
                    if (index >= state.objects.size - 1 && !state.endReached && !state.loading.justLoading) {
                        onLoadObjects()
                    }
                    MetObjectView(item) {

                    }
                }
                item {
                    if (state.loading.justLoading && state.objects.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(36.dp))
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            visible = state.loading.justLoading && state.objects.isEmpty(),
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> -fullHeight },
            ) + fadeIn(),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> -fullHeight },
            ) + fadeOut(),
        ) {
            LoadingView(
                modifier = Modifier.align(Alignment.Center),
            )
        }
    }
}