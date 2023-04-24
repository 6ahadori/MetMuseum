package com.bahadori.metropolitanmuseum.core.designsystem.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp


fun Modifier.fadeEdge(
    colors: List<Color>,
    alpha: Float = 0.99F,
    blendMode: BlendMode = BlendMode.Darken,
    height: Dp? = null,
): Modifier {
    return this
        .graphicsLayer(alpha = alpha)
        .drawWithContent {
            drawContent()
            drawRect(
                brush = Brush.verticalGradient(colors),
                blendMode = blendMode,
                size = if (height != null) {
                    Size(drawContext.size.width, height.value)
                } else drawContext.size
            )
        }
}