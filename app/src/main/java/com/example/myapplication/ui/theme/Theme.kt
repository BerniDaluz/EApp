package com.example.myapplication.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

//all colors used in app

object EnvironmentalColors {
    //background colors
    //main background  - navy dark
    val DarkBackground = Color(0xFF0A0E27)

    //ligther surface color
    val SurfaceColor = Color(0xFF1A1F3A)

    //background for cards and containers
    val CardBackground = Color(0xFF151B33)

    //accent colors
    //colors for highlighting elements
    val CyanAccent = Color(0xFF00D9FF)
    val TurquoiseAccent = Color(0xFF00E5CC)
    val GreenAccent = Color(0xFF00FF88)

    //status colors
    //green for good air quility
    val GoodGreen = Color(0xFF00E5CC)

    //yellow  for moderate air quility
    val ModerateYellow = Color(0xFFFFD700)

    //red for poor air quility
    val PoorRed = Color(0xFFFF4444)

    //text colors
    val TextPrimary = Color(0xFFFFFFFF)
    val TextSecondary = Color(0xFFB0B8D4)
    val TextTertiary = Color(0xFF7A8299)

    //chart Colors
    val ChartCyan = Color(0xFF00D9FF)
    val ChartGradientStart = Color(0xFF00D9FF)
    val ChartGradientEnd = Color(0xFF0099CC)


}
//darkcolor scheme for the app
private val DarkColorCheme = darkColorScheme(
    //primary color - buttons -highlights- imp elements
    primary = EnvironmentalColors.CyanAccent,

    //2nd color - buttons - accents
    secondary = EnvironmentalColors.TurquoiseAccent,

    //3rd color for other accents
    tertiary = EnvironmentalColors.GreenAccent,

    //mainbackground color for app
    background = EnvironmentalColors.DarkBackground,

    //sueface color - cards -dialogs-surface
    surface = EnvironmentalColors.SurfaceColor,

    //text coolor background
    onBackground = EnvironmentalColors.TextPrimary,

    //text color on surface
    onSurface = EnvironmentalColors.TextPrimary,

)

@Composable
//content displayed inside theme
fun EnvironmentalMonitoringTheme(content: @Composable () -> Unit){
    //apply dark color scheme - martial design theme
    MaterialTheme(
        colorScheme = DarkColorCheme,
        content = content
    )
}