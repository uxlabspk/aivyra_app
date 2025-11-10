package com.codehuntspk.aivyra.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// Dark color scheme matching the Aivyra landing page
private val AivyraDarkColorScheme = darkColorScheme(
    primary = Cyan400,
    onPrimary = Color.White,
    primaryContainer = Cyan500,
    onPrimaryContainer = Color.White,

    secondary = Purple500,
    onSecondary = Color.White,
    secondaryContainer = Purple600,
    onSecondaryContainer = Color.White,

    tertiary = Pink400,
    onTertiary = Color.White,
    tertiaryContainer = Pink500,
    onTertiaryContainer = Color.White,

    background = Slate900,
    onBackground = TextWhite,

    surface = SurfaceWhite5,
    onSurface = TextWhite,
    surfaceVariant = SurfaceWhite10,
    onSurfaceVariant = TextWhite70,

    surfaceTint = Cyan400,
    inverseSurface = TextWhite,
    inverseOnSurface = Slate900,

    error = Error,
    onError = Color.White,
    errorContainer = Error,
    onErrorContainer = Color.White,

    outline = BorderWhite10,
    outlineVariant = BorderWhite20,

    scrim = Color.Black.copy(alpha = 0.5f)
)

// Light color scheme (optional - for light theme support)
private val AivyraLightColorScheme = lightColorScheme(
    primary = Cyan500,
    onPrimary = Color.White,
    primaryContainer = Cyan400,
    onPrimaryContainer = Slate900,

    secondary = Purple600,
    onSecondary = Color.White,
    secondaryContainer = Purple500,
    onSecondaryContainer = Slate900,

    tertiary = Pink500,
    onTertiary = Color.White,
    tertiaryContainer = Pink400,
    onTertiaryContainer = Slate900,

    background = Color(0xFFF8FAFC),
    onBackground = Slate900,

    surface = Color.White,
    onSurface = Slate900,
    surfaceVariant = Color(0xFFF1F5F9),
    onSurfaceVariant = Slate800,

    surfaceTint = Cyan500,
    inverseSurface = Slate900,
    inverseOnSurface = Color.White,

    error = Error,
    onError = Color.White,
    errorContainer = Color(0xFFFEE2E2),
    onErrorContainer = Color(0xFF7F1D1D),

    outline = Color(0xFFE2E8F0),
    outlineVariant = Color(0xFFF1F5F9),

    scrim = Color.Black.copy(alpha = 0.5f)
)

@Composable
fun AivyraTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Set to false to always use dark theme (matching the landing page)
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    // Use dark theme by default to match the landing page aesthetic
    val colorScheme = if (darkTheme) {
        AivyraDarkColorScheme
    } else {
        AivyraLightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).apply {
                isAppearanceLightStatusBars = !darkTheme
                isAppearanceLightNavigationBars = !darkTheme
            }
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}