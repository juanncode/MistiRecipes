package com.github.juanncode.mistirecipes.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = MistiBlue,
    background = MistiBlack,
    surface = MistiDarkGray,
    secondary = MistiWhite,
    tertiary = MistiWhite,
    primaryContainer = MistiBlue30,
    onPrimary = MistiBlack,
    onBackground = MistiWhite,
    onSurface = MistiWhite,
    onSurfaceVariant = MistiGray,
    error = MistiDarkRed,
    errorContainer = MistiDarkRed5
)

@Composable
fun MistiRecipesTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}