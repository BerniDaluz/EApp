package com.example.myapplication.ui.theme

// Material design
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.Typography

// Composable annotation
import androidx.compose.runtime.Composable

// Colors
import com.example.myapplication.ui.theme.EnvironmentalColors

// All colors used in app
// Dark color scheme
// Dark color scheme for the app
private val DarkColorScheme = darkColorScheme(
    // Primary color - buttons - highlights - important elements
    primary = EnvironmentalColors.CyanAccent,

    // Secondary color - buttons - accents
    secondary = EnvironmentalColors.TurquoiseAccent,

    // Tertiary color for other accents
    tertiary = EnvironmentalColors.GreenAccent,

    // Main background color for app
    background = EnvironmentalColors.DarkBackground,

    // Surface color - cards - dialogs - surface
    surface = EnvironmentalColors.SurfaceColor,

    // Text color on background
    onBackground = EnvironmentalColors.TextPrimary,

    // Text color on surface
    onSurface = EnvironmentalColors.TextPrimary,
)

// Default Material 3 Typography
private val DefaultTypography = Typography()

// Content displayed inside theme
@Composable
fun EnvironmentalMonitoringTheme(content: @Composable () -> Unit) {
    // Apply dark color scheme - Material design theme
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = DefaultTypography,
        content = content
    )
}
