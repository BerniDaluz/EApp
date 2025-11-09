package com.example.myapplication.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


//display circular progress indicator foe air quality

//@param aqi // air quality index value
//@param modifier // mod for styling

@Composable
fun AQIGauge( aqi: Int, modifier: Modifier = Modifier){
    //deterim status text base on aqi value
    val aqiStatus = when {
        aqi <= 50 -> "GOOD"
        aqi <= 100 -> "MODERATE"
        aqi <= 150 -> "UNHEALTHY FOR SENSITIVE"
        aqi <= 200 -> "UNHEALTHY"
        aqi <= 300 -> "VERY UNHEALTHY"
        else -> "HAZARDOUS"
    }

    //deterim color based on aqi value
    val gaugeColor = when {
        aqi <= 50 -> EnvironmentalColors.GoodGreen// for good
        aqi <= 100 -> Color(0xFFFFD700)//for moderate
        aqi <= 150 -> Color(0xFFFF8C00)//for unhealthy
        aqi <= 200 -> Color(0xFFFFD700)//for unhealthy
        else -> Color(0xFF8B0000) // for very unhealthy

    }
    //container for drawing custom graphics
    Box(
        modifier = modifier
            .size(200.dp)//size of gauge 200X200 DP
            .drawBehind{
                //draw graphic behind content
                val strokeWidth = 12f
                val radius = (size.minDimension / 2) - (strokeWidth / 2)
                val centerX = size.width / 2
                val centerY = size.height / 2

                //background arc
                drawArc(
                    color = Color(0xFF2A2F4A),
                    startAngle = 135f,//start angle top-left
                    sweepAngle =270f,//sweep 270 degrees 3/4
                    useCenter = false,//don't fill center
                    topLeft =  androidx.compose.ui.geometry.Offset(
                        centerX - radius,
                        centerY - radius
                    ),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                    style = Stroke(strokeWidth) // draw as stroke , not filled

                )
                //colored arc based aqi value
                //arc fills based on how high aqi
                val sweepAngle = (aqi.coerceIn(0, 300) / 300f) * 270f //calc how much to fill
                drawArc(
                    color = gaugeColor,
                    startAngle = 135f,
                    sweepAngle = sweepAngle,
                    useCenter = false,
                    topLeft = androidx.compose.ui.geometry.Offset(
                        centerX - radius,
                        centerY - radius
                    ),
                    size = androidx.compose.ui.geometry.Size(radius * 2, radius * 2),
                    style = Stroke(strokeWidth)
                )
            },
        contentAlignment = Alignment.Center //center content inside the box
    ) {
        //displaying aqi value - status in center if gauage
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            Text(
                "AQI",
                color = EnvironmentalColors.CyanAccent,
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
            //actual aqi num in large white text
            Text(
                aqi.toString(),
                color = EnvironmentalColors.TextPrimary,
                fontSize = 56.sp,
                fontWeight = FontWeight.Bold
            )
            //status text in color matching the gauge
            Text(
                aqiStatus,
                color = gaugeColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold

            )

        }

    }
}
//card shows a single metric ex temp or humidity
@Composable
fun MetricCard(
    label: String,
    value: String,
    modifier: Modifier = Modifier
){
    //card -. raised container
    Card(
        modifier = modifier
            .fillMaxWidth()//fill width of parent
            .padding(8.dp),//add padding around card
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = EnvironmentalColors.CardBackground//dark card background
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)//shadow effect
    ){
        //arrange items vertically
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
            //center items horizontally
        ){
            //label text
            Text(
                label,
                color = EnvironmentalColors.TextSecondary,// light grey color
                fontSize =  12.sp,
                fontWeight = FontWeight.Light
            )
            //space between label and value
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                value,
                color = EnvironmentalColors.TextPrimary,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold

            )
        }

    }

    //simplelinechart -> displays data trends
    @Composable
    fun SimpleLineChart(
        dataPoints: List<Float>,//list plot values
        modifier: Modifier = Modifier,
        height: Float = 100f//default height is 100 dp
    ){
        //return early if no data points
        if(dataPoints.isEmpty()) return
        Box(
            //contain chart
            modifier = modifier
                .fillMaxWidth()
                .height(height.dp)
                .background(EnvironmentalColors.DarkBackground)
            //dark background
        ){
            Canvas(
                modifier = Modifier
                    .fillMaxSize()//fill entire box
                    .padding(8.dp)//add padding around chart
            ) {
                if (dataPoints.size < 2) return@Canvas
                //find max and min values to scale
                val maxValue = dataPoints.maxOrNull() ?: 1f
                val minValue = dataPoints.maxOrNull() ?: 0f
                val range = maxValue - minValue

                //convert data points to screeen coordinates
                val points = dataPoints.mapIndexed { index, value ->
                    val x = (index.toFloat() / (dataPoints.size - 1)) * size.width
                    val normalizedValue = if (range == 0f) 0.5f else(value - minValue)/ range
                    val y = size.height - (normalizedValue * size.height)
                    androidx.compose.ui.geometry.Offset(x, y)
                }

                for (i in 0 until points.size -1){
                    // draw line connecting points
                    drawLine(
                        color = EnvironmentalColors.ChartCyan,
                        start = points[i],//start
                        end = points[i + 1],//end
                        strokeWidth = 3f// thickness of line
                    )
                }

            }
        }
    }
}

