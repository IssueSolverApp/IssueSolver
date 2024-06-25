package com.issuesolver.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2981FF),
    onPrimary = Color.White,
    primaryContainer = Color(0xFF0169FE),
    onPrimaryContainer = Color(0xFFE0EDFF),
    secondary = Color(0xFFF09350),
    onSecondary = Color.White,
    secondaryContainer = Color(0xFFEC7722),
    onSecondaryContainer = Color(0xFFE67B2E),
    error = Color(0xFFEF5648),
    onError = Color.White,
//    background = Color(0xFFF0F4F9),
    onBackground = Color(0xFF000B1B),
    surface = Color.White,
    onSurface = Color(0xFF1A1A1A),
//    surfaceVariant = Color(0xFFD4DBE6),
    onSurfaceVariant = Color(0xFFF0F4F9),
    inverseSurface = Color(0xFF429A60),
    inverseOnSurface = Color(0xFF56B777),
    inversePrimary = Color(0xFF0C5ED9),
)

@Composable
fun IssueSolverTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}