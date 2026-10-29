package com.duoc.TecnicoQCC.ui.theme

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

// --- Tema Claro ---
private val LightColorScheme = lightColorScheme(
    primary = QCCDarkBlue,
    secondary = QCCBlue,
    background = QCCLightBackground,
    surface = QCCWhite,
    onPrimary = QCCWhite,
    onSecondary = QCCWhite,
    onBackground = QCCDarkBlue,
    onSurface = QCCDarkBlue,
    onSurfaceVariant = QCCGrayText
)

// --- Tema Oscuro (Opcional, por si el celular está en modo oscuro) ---
private val DarkColorScheme = darkColorScheme(
    primary = QCCBlue,
    secondary = QCCDarkBlue,
    background = Color(0xFF121212),
    surface = Color(0xFF1E1E1E),
    onPrimary = QCCWhite,
    onSecondary = QCCWhite,
    onBackground = QCCWhite,
    onSurface = QCCWhite,
    onSurfaceVariant = Color(0xFFAAAAAA)
)

@Composable
fun TecnicoQCCTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    // Cambia el color de la barra de estado del celular (donde está la batería y la hora)
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}