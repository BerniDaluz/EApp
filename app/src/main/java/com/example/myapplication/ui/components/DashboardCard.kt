package com.example.myapplication.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.EnvironmentalColors

//dashboardcard section
@Composable
fun DashboardCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = EnvironmentalColors.CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ){
        Column (
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth()
        ){
            Text(
                text = title,
                color = EnvironmentalColors.TextPrimary,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            content()
        }
    }
}