package com.example.myapplication.ui.theme

//martial design
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme

//composable annotation
import androidx.compose.runtime.Composable

//colors + typography
import com.example.myapplication.ui.theme.EnvironmentalColors
import com.example.myapplication.ui.theme.Typography

//all colors used in app
//Dark color scheme
//darkcolor scheme for the app
private val DarkColorScheme = darkColorScheme(
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

//content displayed inside theme
@Composable
fun EnvironmentalMonitoringTheme(content: @Composable () -> Unit){
    //apply dark color scheme - martial design theme
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = Typography,
        content = content
    )
}