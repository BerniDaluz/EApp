package com.example.myapplication

//android core
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

//compose runetime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

//layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier

//costum theme
import com.example.myapplication.ui.theme.EnvironmentalMonitoringTheme

//3 screens
import com.example.myapplication.ui.screens.DashboardScreen
import com.example.myapplication.ui.screens.MapScreen
import com.example.myapplication.ui.screens.CommunityScreen

//viewmodel state management
import com.example.myapplication.viewmodel.AppViewModel

//import bottom navigationBar
import com.example.myapplication.ui.components.BottomNavigationBar


//main screen of app
//begining of the app
class MainActivity : ComponentActivity(){

    //func runs when app starts
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //display  compose ui
        setContent {
            //dark theme with cyan colors
            EnvironmentalMonitoringTheme {
                //main dashboard screen
                MainAppContent()
            }
        }
    }

}

// main content of  the app
//creates viewModel die state mangement
//sets scaffold layout
//display current screen
//shows bottom nav Bar

@Composable
fun MainAppContent(){
    //viewmodel - manages screens
    val viewModel = remember { AppViewModel() }
    Scaffold (
        bottomBar = {
            BottomNavigationBar(
                currentScreen = viewModel.currentScreen,
                onNavigate = { screenName -> // on click , navigate to that screen
                    viewModel.navigateTo(screenName)
                }
            )
        }
    ){ paddingValues ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ){
            //screen nav
            when (viewModel.currentScreen){
                "Dashboard" -> {
                    DashboardScreen()
                }
                "Map" -> {
                    MapScreen()
                }
                "Community" ->{
                    CommunityScreen()
                }
                else -> {
                    DashboardScreen()
                }
            }
        }
    }
}
