package com.bahadori.metropolitanmuseum.core.designsystem.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.bahadori.designsystem.component.StatusBarColor
import com.bahadori.designsystem.theme.DarkBackground
import com.bahadori.designsystem.theme.DarkError
import com.bahadori.designsystem.theme.DarkErrorContainer
import com.bahadori.designsystem.theme.DarkInverseOnSurface
import com.bahadori.designsystem.theme.DarkInversePrimary
import com.bahadori.designsystem.theme.DarkInverseSurface
import com.bahadori.designsystem.theme.DarkOnBackground
import com.bahadori.designsystem.theme.DarkOnError
import com.bahadori.designsystem.theme.DarkOnErrorContainer
import com.bahadori.designsystem.theme.DarkOnPrimary
import com.bahadori.designsystem.theme.DarkOnPrimaryContainer
import com.bahadori.designsystem.theme.DarkOnSecondary
import com.bahadori.designsystem.theme.DarkOnSecondaryContainer
import com.bahadori.designsystem.theme.DarkOnSurface
import com.bahadori.designsystem.theme.DarkOnSurfaceVariant
import com.bahadori.designsystem.theme.DarkOnTertiary
import com.bahadori.designsystem.theme.DarkOnTertiaryContainer
import com.bahadori.designsystem.theme.DarkOutline
import com.bahadori.designsystem.theme.DarkOutlineVariant
import com.bahadori.designsystem.theme.DarkPrimary
import com.bahadori.designsystem.theme.DarkPrimaryContainer
import com.bahadori.designsystem.theme.DarkScrim
import com.bahadori.designsystem.theme.DarkSecondary
import com.bahadori.designsystem.theme.DarkSecondaryContainer
import com.bahadori.designsystem.theme.DarkSurface
import com.bahadori.designsystem.theme.DarkSurfaceTint
import com.bahadori.designsystem.theme.DarkSurfaceVariant
import com.bahadori.designsystem.theme.DarkTertiary
import com.bahadori.designsystem.theme.DarkTertiaryContainer
import com.bahadori.designsystem.theme.LightBackground
import com.bahadori.designsystem.theme.LightError
import com.bahadori.designsystem.theme.LightErrorContainer
import com.bahadori.designsystem.theme.LightInverseOnSurface
import com.bahadori.designsystem.theme.LightInversePrimary
import com.bahadori.designsystem.theme.LightInverseSurface
import com.bahadori.designsystem.theme.LightOnBackground
import com.bahadori.designsystem.theme.LightOnError
import com.bahadori.designsystem.theme.LightOnErrorContainer
import com.bahadori.designsystem.theme.LightOnPrimary
import com.bahadori.designsystem.theme.LightOnPrimaryContainer
import com.bahadori.designsystem.theme.LightOnSecondary
import com.bahadori.designsystem.theme.LightOnSecondaryContainer
import com.bahadori.designsystem.theme.LightOnSurface
import com.bahadori.designsystem.theme.LightOnSurfaceVariant
import com.bahadori.designsystem.theme.LightOnTertiary
import com.bahadori.designsystem.theme.LightOnTertiaryContainer
import com.bahadori.designsystem.theme.LightOutline
import com.bahadori.designsystem.theme.LightOutlineVariant
import com.bahadori.designsystem.theme.LightPrimary
import com.bahadori.designsystem.theme.LightPrimaryContainer
import com.bahadori.designsystem.theme.LightScrim
import com.bahadori.designsystem.theme.LightSecondary
import com.bahadori.designsystem.theme.LightSecondaryContainer
import com.bahadori.designsystem.theme.LightSurface
import com.bahadori.designsystem.theme.LightSurfaceTint
import com.bahadori.designsystem.theme.LightSurfaceVariant
import com.bahadori.designsystem.theme.LightTertiary
import com.bahadori.designsystem.theme.LightTertiaryContainer
import com.bahadori.designsystem.theme.Typography


private val LightColors = lightColorScheme(
    primary = LightPrimary,
    onPrimary = LightOnPrimary,
    primaryContainer = LightPrimaryContainer,
    onPrimaryContainer = LightOnPrimaryContainer,
    secondary = LightSecondary,
    onSecondary = LightOnSecondary,
    secondaryContainer = LightSecondaryContainer,
    onSecondaryContainer = LightOnSecondaryContainer,
    tertiary = LightTertiary,
    onTertiary = LightOnTertiary,
    tertiaryContainer = LightTertiaryContainer,
    onTertiaryContainer = LightOnTertiaryContainer,
    error = LightError,
    errorContainer = LightErrorContainer,
    onError = LightOnError,
    onErrorContainer = LightOnErrorContainer,
    background = LightBackground,
    onBackground = LightOnBackground,
    outline = LightOutline,
    inverseOnSurface = LightInverseOnSurface,
    inverseSurface = LightInverseSurface,
    inversePrimary = LightInversePrimary,
    surfaceTint = LightSurfaceTint,
    outlineVariant = LightOutlineVariant,
    scrim = LightScrim,
    surface = LightSurface,
    onSurface = LightOnSurface,
    surfaceVariant = LightSurfaceVariant,
    onSurfaceVariant = LightOnSurfaceVariant,
)


private val DarkColors = darkColorScheme(
    primary = DarkPrimary,
    onPrimary = DarkOnPrimary,
    primaryContainer = DarkPrimaryContainer,
    onPrimaryContainer = DarkOnPrimaryContainer,
    secondary = DarkSecondary,
    onSecondary = DarkOnSecondary,
    secondaryContainer = DarkSecondaryContainer,
    onSecondaryContainer = DarkOnSecondaryContainer,
    tertiary = DarkTertiary,
    onTertiary = DarkOnTertiary,
    tertiaryContainer = DarkTertiaryContainer,
    onTertiaryContainer = DarkOnTertiaryContainer,
    error = DarkError,
    errorContainer = DarkErrorContainer,
    onError = DarkOnError,
    onErrorContainer = DarkOnErrorContainer,
    background = DarkBackground,
    onBackground = DarkOnBackground,
    outline = DarkOutline,
    inverseOnSurface = DarkInverseOnSurface,
    inverseSurface = DarkInverseSurface,
    inversePrimary = DarkInversePrimary,
    surfaceTint = DarkSurfaceTint,
    outlineVariant = DarkOutlineVariant,
    scrim = DarkScrim,
    surface = DarkSurface,
    onSurface = DarkOnSurface,
    surfaceVariant = DarkSurfaceVariant,
    onSurfaceVariant = DarkOnSurfaceVariant,
)


@Composable
fun MetTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColors
        else -> LightColors
    }

    StatusBarColor(darkIcons = darkTheme)

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}