@file:OptIn(ExperimentalFoundationApi::class)

package com.bahadori.detail.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.bahadori.designsystem.R
import com.bahadori.designsystem.component.DynamicAsyncImage
import com.bahadori.designsystem.icon.MetIcons

@Composable
fun GalleyView(
    modifier: Modifier = Modifier,
    images: List<String>
) {

    Box(modifier = modifier) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            state = pagerState,
            pageCount = images.size
        ) { index ->
            val image = images[index]
            DynamicAsyncImage(
                imageUrl = image,
                contentDescription = stringResource(id = R.string.object_image),
                contentScale = ContentScale.Fit,
                placeholder = MetIcons.Placeholder,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f))
            )
        }

        Row(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(images.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(6.dp)

                )
            }
        }
    }
}