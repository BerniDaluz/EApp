package com.example.myapplication.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.AQIGauge
import com.example.myapplication.ui.components.DashboardCard
import com.example.myapplication.ui.components.MetricCard
import com.example.myapplication.ui.components.SimpleLineChart
import com.example.myapplication.ui.theme.EnvironmentalColors
import com.example.myapplication.viewmodel.AppViewModel

// Dashboard Screen - displays environmental data and GPS location
@Composable
fun DashboardScreen(viewModel: AppViewModel? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            "Environmental Monitor",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = EnvironmentalColors.TextPrimary
        )

        Spacer(modifier = Modifier.height(16.dp))

        // GPS Location Card
        if (viewModel != null) {
            DashboardCard(
                title = "📍 Current Location",
                modifier = Modifier.padding(8.dp)
            ) {
                if (viewModel.hasLocation) {
                    Text(
                        "Latitude: ${String.format("%.4f", viewModel.userLatitude)}°\n" +
                                "Longitude: ${String.format("%.4f", viewModel.userLongitude)}°",
                        color = EnvironmentalColors.TextSecondary,
                        fontSize = 14.sp
                    )
                } else if (viewModel.locationError != null) {
                    Text(
                        "Location Error: ${viewModel.locationError}",
                        color = EnvironmentalColors.PoorRed,
                        fontSize = 12.sp
                    )
                } else {
                    Text(
                        "Getting location...",
                        color = EnvironmentalColors.TextTertiary,
                        fontSize = 14.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }

        // AQI Gauge
        DashboardCard(
            title = "Air Quality Index",
            modifier = Modifier.padding(8.dp)
        ) {
            AQIGauge(aqi = 45)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Environmental Metrics
        DashboardCard(
            title = "Environmental Metrics",
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
                MetricCard(
                    label = "Temperature",
                    value = "23.5°C"
                )
                MetricCard(
                    label = "Humidity",
                    value = "65%"
                )
                MetricCard(
                    label = "PM2.5",
                    value = "35 µg/m³"
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Temperature Trend
        DashboardCard(
            title = "Temperature Trend",
            modifier = Modifier.padding(8.dp)
        ) {
            SimpleLineChart(
                dataPoints = listOf(20f, 21f, 22f, 23f, 23.5f, 23f, 22f),
                height = 150f
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Air Quality Trend
        DashboardCard(
            title = "Air Quality Trend",
            modifier = Modifier.padding(8.dp)
        ) {
            SimpleLineChart(
                dataPoints = listOf(50f, 48f, 45f, 42f, 40f, 42f, 45f),
                height = 150f
            )
        }
    }
}
