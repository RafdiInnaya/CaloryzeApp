package com.example.myapplication.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

// Dark Color Scheme
private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF0077B6), // Dark Blue
    secondary = Color(0xFF00B4D8), // Light Blue
    tertiary = Color(0xFFFF6F61) // Coral
)

// Light Color Scheme
private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF4CAF50), // Green (Fresh Green)
    secondary = Color(0xFF2196F3), // Blue (Bright Blue)
    tertiary = Color(0xFFFF6F61) // Coral (Warm Coral)
)

@Composable
fun MyApplicationTheme(
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
