package com.bahadori.metropolitanmuseum.core.designsystem.component

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.bahadori.metropolitanmuseum.R
import com.bahadori.metropolitanmuseum.core.designsystem.theme.LocalTintTheme

/**
 * A wrapper around [AsyncImage] which determines the colorFilter based on the theme
 */
@Composable
fun DynamicAsyncImage(
    imageUrl: String,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    @DrawableRes placeholder: Int? = null,
    @DrawableRes error: Int? = null,
    contentScale: ContentScale = ContentScale.Crop,
) {
    val iconTint = LocalTintTheme.current.iconTint
    val request = ImageRequest.Builder(LocalContext.current).apply {
        data(imageUrl)
        placeholder?.let {
            placeholder(placeholder)
        }
        error?.let {
            error(error)
        }
    }.build()

    AsyncImage(
        model = request,
        contentScale = contentScale,
        contentDescription = contentDescription,
        colorFilter = if (iconTint != null) ColorFilter.tint(iconTint) else null,
        modifier = modifier,
    )
}
