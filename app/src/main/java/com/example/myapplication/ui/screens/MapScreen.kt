package com.example.myapplication.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.components.DashboardCard
import com.example.myapplication.ui.theme.EnvironmentalColors
import com.example.myapplication.viewmodel.AppViewModel

// MapScreen - displays map with environmental readings
// Shows user location from GPS sensor
@Composable
fun MapScreen(viewModel: AppViewModel? = null) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(EnvironmentalColors.DarkBackground)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // Title
        Text(
            "Environmental Map",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = EnvironmentalColors.TextPrimary
        )

        // Map placeholder (Google Maps integration would go here)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            colors = CardDefaults.cardColors(
                containerColor = EnvironmentalColors.CardBackground
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .background(EnvironmentalColors.SurfaceColor),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(32.dp)
                ) {
                    Text(
                        "🗺️ Map View",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = EnvironmentalColors.TextPrimary
                    )
                    Text(
                        "Google Maps integration coming soon",
                        fontSize = 12.sp,
                        color = EnvironmentalColors.TextSecondary,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }

        // GPS Location Info
        if (viewModel != null) {
            DashboardCard(
                title = "📍 Your Location",
                modifier = Modifier.padding(8.dp)
            ) {
                if (viewModel.hasLocation) {
                    Column {
                        Text(
                            "Latitude: ${String.format("%.6f", viewModel.userLatitude)}°",
                            color = EnvironmentalColors.TextSecondary,
                            fontSize = 12.sp
                        )
                        Text(
                            "Longitude: ${String.format("%.6f", viewModel.userLongitude)}°",
                            color = EnvironmentalColors.TextSecondary,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                } else if (viewModel.locationError != null) {
                    Text(
                        "Error: ${viewModel.locationError}",
                        color = EnvironmentalColors.PoorRed,
                        fontSize = 12.sp
                    )
                } else {
                    Text(
                        "Acquiring GPS location...",
                        color = EnvironmentalColors.TextTertiary,
                        fontSize = 12.sp
                    )
                }
            }
        }

        // Nearby Readings
        DashboardCard(
            title = "📊 Nearby Readings",
            modifier = Modifier.padding(8.dp)
        ) {
            Column {
                Text(
                    "• View readings by location",
                    color = EnvironmentalColors.TextSecondary,
                    fontSize = 12.sp
                )
                Text(
                    "• Filter by distance",
                    color = EnvironmentalColors.TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    "• Compare with nearby areas",
                    color = EnvironmentalColors.TextSecondary,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}
