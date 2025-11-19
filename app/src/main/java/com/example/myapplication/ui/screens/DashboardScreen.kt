package com.example.myapplication.ui.screens
//import layout component
import  androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.verticalScroll
import androidx.compose.material3.Text

//import color + styling
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//import custom components
import com.example.myapplication.ui.components.AQIGauge
import com.example.myapplication.ui.components.MetricCard
import com.example.myapplication.ui.components.DashboardCard
import com.example.myapplication.ui.components.SimpleLineChart
import com.example.myapplication.ui.components.EnvironmentalColors

//1st screen user when app is open
@Composable
fun DashboardScreen(){
    //colum arranges items vertically
    Column(
        modifier = Modifier
            .fillMaxSize() //fill entore screen
            .padding(16.dp) //add padding around
            .verticalScroll(rememberScrollState()), // allow scrolling if content too long

        horizontalALignment = Alignment.CenterHorizontally
    ){
        //Title
        //Display screen title
        Text(
            "Enviornmental Dashboard",
            color = EnvironmentalColors.TextPrimary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
        //Add space between title + content
        Spacer(modifier = Modifier.height(24.dp))

        /// AQI Gauge Section ///
        //diplay the circular air quality guage
        DashboardCard(title = "Air Quality Index") {
            AQIGauge(
                aqi = 45,
                modifier = Modifier.padding(16.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))
    }
    /// Temp + Humidity Section ///
    //display temp + humidity in 2 cxards side by side
    DashboardCard(tit;le = "Current Conditions"){
        //create row with 2 metric cars
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxSize(),// fill availble width
        ){
            //temp card
            MetricCard(
                label = "Temperature",
                value = "24°C",
                modifier = Modifier.weight(1f)
            )
            //Humidity card
            MetricCard(
            label = "humidity",
            value = "60%",
            modifier = Modifier.weight(1f)//take half widith
            )
        }

    Spacer(modifier = Modifier.height(16.dp))
    ///C02 Chart Section //
    //display c02 levels over last 24 hours
    DashboardCard(tilte = "CO₂ Levels - 24h "){
        //CO2 values in PMM
        val co2Data = listOf(460f, 462f, 461f,463f, 462f, 461f, 460f)
        SimpleLineChart(
            dataPoints = co2Data,
            height = 100f,
            modifier = Modifier.padding(8.dp) //add padding around chart
        )
    }

    Spacer(modifier = Modifier.height(16.dp))


    /// PM2.5 + PM10 Section ///
    DashboardCard(title = "Particulate Matter") {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = androidx.compose.founation, Arrangement.spacedBy(8.dp)
        ) {
            //Pm2.5 Card
            MetricCard(
                label = "PM2.5",
                value = "12 µg/m³",
                modifier = Modifier.weight(1f)
            )
            //PM10 card
            MetricCard(
                label = "PM10",
                value = "25 µg/m³",
                modifier = Modifier.weight(1f)
            )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

    }
}




