package com.example.myapplication


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.AQIGauge
import com.example.myapplication.ui.DashboardCard
import com.example.myapplication.ui.EnvironmentalColors
import com.example.myapplication.ui.EnvironmentalMonitoringTheme
import com.example.myapplication.ui.MetricCard
import com.example.myapplication.ui.theme.SimpleLineChart

//main screen of app
//begining of the app
class MainActivity : ComponentActivity(){

    //func runs when app starts
    override fun onCreate(savedInstaceState: Bundle?){
        super.onCreate(savedInstaceState)

        //display the jetpack compose ui
        setContent {
            //dark theme with cyan colors
            EnvironmentalMonitoringTheme {
                //main dashboard screen
                DashboardScreen()
            }
        }
    }

}

// main dashboard for envi data
//user pov

@Composable
fun DashboardScreen(){
    //scrollable colum
    Column(
        modifier = Modifier
            .fillMaxSize()//fills entire screen
            .background(EnvironmentalColors.DarkBackground)//navy background
            .verticalScroll(rememberScrollState())//allows scrolling
            .padding(16.dp), //adds padding around edges
            horizontalAlignment = Alignment.CenterHorizontally//centres the conten horiz
    ){
        //ads space at top
        Spacer(modifier = Modifier.height(24.dp))

        //title section
        Text(
            text = "Enviromental\nMonitoring Dashboard",
            fontSize = 28.sp, //big text size
            fontWeight = FontWeight.Bold,//makes it bold
            color = EnvironmentalColors.TextPrimary,//white text color
            modifier = Modifier.padding(bottom = 24.dp)
        )

        //AQI gauage
        //showing circular air quility indicator
        AQIGauge(
            aqi = 45,//air quailty
            modifier = Modifier.padding(24.dp)
        )
        //adds space between sections
        Spacer(modifier = Modifier.height(24.dp))

        //temp and humidity cards
        //row of two cards
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)//space between cards
        ){
            //left card temp
            MetricCard(
                label = "Temperature",
                value = "24°C",
                modifier = Modifier.weight(1f)//takes half the width
            )

            //right card Humidity
            MetricCard(
                label = "Humidity",
                value = "60%",
                modifier = Modifier.weight(1f)// take width half space

            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // C02 cart section
        //shows card with line chart for C02 levels

        DashboardCard(
            title = "C0₂ - 24h",//title card
            modifier = Modifier.padding(8.dp)
        ){
            //sample dat for chart
            val co2Data = listOf(460f, 462f, 461f, 463f, 462f, 461f, 460f)

            //diplay line chart
            SimpleLineChart(
                dataPoints = co2Data,
                height = 100f
            )

            //labels below chart
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                // time labels
                Text("6h", color = EnvironmentalColors.TextTertiary, fontSize = 10.sp)
                Text("6h", color = EnvironmentalColors.TextTertiary, fontSize = 10.sp)
                Text("12h", color = EnvironmentalColors.TextTertiary, fontSize = 10.sp)
                Text("18h", color = EnvironmentalColors.TextTertiary, fontSize = 10.sp)
                Text("24h", color = EnvironmentalColors.TextTertiary, fontSize = 10.sp)





            }
        }
        //add space to bottom
        Spacer(modifier = Modifier.height(24.dp))

        //additional mertrics section
        DashboardCard(
            title = "Air Quaity Details",
            modifier = Modifier.padding(8.dp)
        ){
            //colum with more metrics
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                //pm2.5 metric
                MerticRow(
                    label = " PM2.5",
                    value = "12 µg/m³ "
                )

                //PM10 metric
                MerticRow(
                    label = "PM10",
                    value = "25 µg/m³"
                )

                //c02 level metric
                MerticRow(
                    label = "C0₂ Level",
                    value= "460 pm"
                )

            }
        }

        //Add space at bottom
        Spacer(modifier = Modifier.height(24.dp))
    }

}
//metric row to show label and values
@Composable
fun MerticRow(
    label: String,//label
    value: String// value
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,//label and value spaced apart
        verticalAlignment = Alignment.CenterVertically
    ){
        //label left
        Text(
            text = label,
            color = EnvironmentalColors.TextSecondary,
            fontSize = 14.sp
        )
        //Value right
        Text(
            text = value,
            color = EnvironmentalColors.TextPrimary,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
}