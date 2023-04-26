@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.bahadori.metropolitanmuseum.feature.search.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.common.loading.LoadState
import com.bahadori.metropolitanmuseum.common.loading.justLoading
import com.bahadori.metropolitanmuseum.core.designsystem.component.LoadingView
import com.bahadori.metropolitanmuseum.core.designsystem.component.MetTextField
import com.bahadori.metropolitanmuseum.core.designsystem.component.StatusBarColor
import com.bahadori.metropolitanmuseum.core.designsystem.component.fadeEdge
import com.bahadori.metropolitanmuseum.feature.search.presentation.SearchContract.*
import com.bahadori.metropolitanmuseum.feature.search.presentation.component.MetObjectView

@Composable
internal fun SearchRoute(
    onObjectClicked: (Int) -> Unit,
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
        },
        onObjectClicked = onObjectClicked
    )

}

@Composable
internal fun SearchScreen(
    state: State,
    onQueryChanged: (String) -> Unit,
    onLoadObjects: () -> Unit,
    onObjectClicked: (Int) -> Unit,
) {

    val gridState = rememberLazyGridState()
    val keyboardController = LocalSoftwareKeyboardController.current

    StatusBarColor(darkIcons = !isSystemInDarkTheme())

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            MetTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                label = R.string.search,
                value = state.query,
                trailingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = stringResource(id = R.string.search_icon)
                    )
                },
                onValueChange = onQueryChanged,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onSearch = { keyboardController?.hide() }
                ),
            )
            LazyVerticalGrid(
                columns = GridCells.Adaptive(120.dp),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxSize()
                    .testTag("search"),
                state = gridState,
            ) {
                items(state.objects.size) { index ->
                    val item = state.objects[index]
                    if (index >= state.objects.size - 1 && !state.endReached && !state.loading.justLoading) {
                        onLoadObjects()
                    }
                    MetObjectView(item) { obj ->
                        onObjectClicked(obj.objectID)
                    }
                }
                item {
                    if (state.loading.justLoading && state.objects.isNotEmpty()) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(36.dp))
                        }
                    }
                }
            }
        }
        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
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

        AnimatedVisibility(
            modifier = Modifier.align(Alignment.Center),
            visible = state.objects.isEmpty(),
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.size(72.dp),
                    painter = painterResource(id = R.drawable.ic_empty_set),
                    contentDescription = stringResource(id = R.string.empty_list)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.no_item_to_show),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}