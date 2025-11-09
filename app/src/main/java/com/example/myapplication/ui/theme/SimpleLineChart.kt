package com.example.myapplication.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.EnvironmentalColors

@Composable
fun SimpleLineChart(
    dataPoints: List<Float>,
    height: Float,
    modifier: Modifier = Modifier
){
    if (dataPoints.isEmpty()){
        return
    }
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
    ) {
        val width = size.width
        val canvasHeight = size.height
        val minDataValue = dataPoints.minOrNull() ?: 0f
        val maxDataValue = dataPoints.maxOrNull() ?: 1f

        val valueRange = if(maxDataValue - minDataValue == 0f)
            1f else maxDataValue - minDataValue
        val path = Path()

        val xStep =width/ (dataPoints.size -1).toFloat()
        dataPoints.forEachIndexed { index, value ->
            val x = index * xStep
            val y = canvasHeight - ((value - minDataValue) / valueRange)

            if(index == 0){
                path.moveTo(x,y)
            }else{
                path.lineTo(x,y)
            }
        }

        drawPath(
            path = path,
            color = EnvironmentalColors.ChartCyan,
            style = Stroke(width = 4.dp.toPx())
        )
    }
}