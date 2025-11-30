package com.example.myapplication.ui.screens

import androidx.compose.runtime.Composable

//import Layout components
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text

//import color+ styling
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//import custom theme
import com.example.myapplication.ui.theme.EnvironmentalColors

//MapScreen display environmental reading on map

@Composable
fun MapScreen(){
    //arrange item vertical
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        ///Title///
        Text(
            "Environmental Map",
            color = EnvironmentalColors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.padding(top = 16.dp)
        )

        ///Map Placeholder ///
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            contentAlignment = Alignment.Center
        ){
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxSize()
            ){
                // Icon placeholder
                Text(
                    "📍", // map pin emoji
                    fontSize = 64.sp // large emoji
                )
                //explantion text
                Spacer(
                    modifier = Modifier.padding( top = 16.dp)
                )

                Text(
                    "Map View",
                    color = EnvironmentalColors.TextPrimary,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(
                    modifier = Modifier.padding( top = 8.dp)
                )

                Text(
                    "Environmental data will appear on map",
                    color = EnvironmentalColors.TextSecondary,
                    fontSize = 14.sp
                )
                //possible future implementation
                Spacer(
                    modifier = Modifier.padding( top = 16.dp)
                )
                Text(
                    "Integrate Google Maps or Mapbox to show map",
                    color = EnvironmentalColors.TextTertiary,
                    fontSize = 12.sp

                )
            }
        }

        //Info Section //
        //display info about map features
        Spacer(
            modifier = Modifier.padding( top = 16.dp)
        )

        //info card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = EnvironmentalColors.CardBackground
            )
        ){
            Column (
                modifier = Modifier.padding(16.dp)
            ){
                //info title
                Text(
                    "Map Features",
                    color = EnvironmentalColors.TextPrimary,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                //FEATURE 1
                Spacer(
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    "• View readings by location",
                    color = EnvironmentalColors.TextSecondary,
                    fontSize = 14.sp
                )
                //feature 2
                Spacer(
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    "• Color-coded AQI markers",
                    color = EnvironmentalColors.TextSecondary,
                    fontSize = 14.sp
                )
                //feature 3
                Spacer(
                    modifier = Modifier.padding( top = 4.dp)
                )
                Text(
                    "• Click markers for details",
                    color = EnvironmentalColors.TextSecondary,
                    fontSize = 14.sp
                )
            }
        }
    }
}