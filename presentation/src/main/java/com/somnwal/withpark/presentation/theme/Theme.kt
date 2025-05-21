package com.somnwal.withpark.presentation.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Orange01,
    onPrimary = Grey05,
    error = Error,
    background = Grey95,
    onBackground = Grey10,
    surface = Grey90,
    surfaceTint = Grey85,
    secondaryContainer = Grey80,
    onSecondaryContainer = Grey40,
    onSurface = Grey10,
    onSurfaceVariant = Grey15,
    outline = Grey80,
    outlineVariant = Grey10,
)

private val LightColorScheme = lightColorScheme(
    primary = Orange01,
    onPrimary = Grey05,
    error = Error,
    background = Grey95,
    onBackground = Grey10,
    surface = Grey90,
    surfaceTint = Grey85,
    secondaryContainer = Grey80,
    onSecondaryContainer = Grey40,
    onSurface = Grey10,
    onSurfaceVariant = Grey15,
    outline = Grey80,
    outlineVariant = Grey10,
)

@Composable
fun WithparkTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}