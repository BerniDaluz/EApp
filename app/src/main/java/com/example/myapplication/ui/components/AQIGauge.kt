package com.example.myapplication.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset



import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.EnvironmentalColors

//AQI Gauge - circular progress indicator showing air quality
@Composable
fun AQIGauge(
    aqi: Int,
    modifier: Modifier = Modifier
){
    //dete color base on aqi value
    val color = when {
        aqi <= 50 -> EnvironmentalColors.GoodGreen
        aqi <= 100 -> EnvironmentalColors.ModerateYellow
        else -> EnvironmentalColors.PoorRed
    }

    //dete status text
    val status = when {
        aqi <= 50 -> "GOOD"
        aqi <= 100 -> "MODERATE"
        else -> "UNHEALTHY"
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier.size(200.dp),
            contentAlignment = Alignment.Center
        ){
            //draw circular guage
            Canvas(modifier = Modifier.fillMaxSize()) {
                val radius = size.minDimension / 2
                val centerOffset = Offset(
                    x = size.width / 2f,
                    y = size.height / 2f
                )

                //background circle
                drawCircle(
                    color = EnvironmentalColors.CardBackground,
                    radius = radius,
                    center = center

                )
                //progress arc
                val sweepAngle = (aqi / 500f) * 360f
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    size = size,
                    style = Stroke(width = 20f)


                )
            }
            //aqi value in center
            Column( horizontalAlignment = Alignment.CenterHorizontally
            ){
                Text(
                    text = aqi.toString(),
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = EnvironmentalColors.TextPrimary
                )
                Text(
                    text = status,
                    fontSize = 16.sp,
                    color = color
                )
            }
        }
    }
}
