package com.example.myapplication.ui.components

//libs
//material design nav var components
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text

//icons nav items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.People

//layout + modifier utilities
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

//import color util
import androidx.compose.ui.graphics.Color
import com.example.myapplication.ui.theme.EnvironmentalColors

//nav bar at bottom of screen
//3 nav buttins for home/map/people

@Composable
fun BottomNavigationBar(
    currentScreen: String,//screen currently shown
    onNavigate: (String) -> Unit //calling fun when clicked
){
    //navBar -> bottm nav bar container
    NavigationBar(
        //set background color to matchb dark theme
        containerColor = EnvironmentalColors.CardBackground,

        //set height of the nav var
        modifier = Modifier.height(64.dp)
    ){
        //nav item1 : DashBoard
        NavigationBarItem(
            //icon display
            icon = {
                Icon(
                    imageVector = Icons.Filled.Home,//home icon
                    contentDescription = "Dashboard", //desc for access
                    //color icon base on whether item is selected
                    tint = if (currentScreen == "Dashboard"){
                        EnvironmentalColors.CyanAccent//if selected
                    } else{
                        EnvironmentalColors.TextTertiary//if not selectd
                    }
                )
            },
            //label text display below the icon
            label = {
                Text(
                    "Dashboard",
                    //color text based on item selected
                    color = if (currentScreen == "Dashboard"){
                        EnvironmentalColors.CyanAccent//if selected
                    } else{
                        EnvironmentalColors.TextTertiary//if not selectd
                    }
                )
            },
            //if item currently seleceted?
            selected = currentScreen == "Dashbord",
            //what happens on user clicks item
            onClick = {
                //call onNav with dashboard -> go to dashboard screen
                onNavigate("Dashboard")
            }
        )
        //Navigation Item 2: Map
        NavigationBarItem(
            //icon display
            icon = {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    //location/mao icob
                    contentDescription = "Map", // desc for access
                    //color the icon based based on item selected
                    tint = if (currentScreen == "Map"){
                        EnvironmentalColors.CyanAccent//if  selectd
                    } else{
                        EnvironmentalColors.TextTertiary//if not selectd
                        }
                )
            },
            // label test display below the icon
            label = {
                Text(
                    "Map",
                    //color text based on whether item is selected
                    color = if (currentScreen == "Map"){
                        EnvironmentalColors.CyanAccent//if selectd
                    } else {
                        EnvironmentalColors.TextTertiary//if not selectd
                    }
                )
            },
            //is item currently selected?
            selected = currentScreens == "Map",

            //what happens when user click
            onClick = {
                //call onNav with map -> to go to map screen
                onNavigate("Map")
            }
        )

        //Nav Item 3: Community
        NavigationBarItem(
            //icon display
            icon = {
                Icon(
                    imageVector = Icons.Filled.People,//community icons
                    contentDescription = "Community", //desc fro access

                    //color icon base on item selection
                    tint = if (currentScreen == "Community"){
                        EnvironmentalColors.CyanAccent//if selectd
                    }else{
                        EnvironmentalColors.TextTertiary//if not selectd
                    }
                )
            },
            //label text display below icon
            label = {
                Text(
                    "Community",
                    //color text based on item selected
                    color = if(currentScreen == "Community"){
                        EnvironmentalColors.CyanAccent//if selectd
                    } else{
                        EnvironmentalColors.TextTertiary//if not selectd

                    }
                )
            },
            //is itemm selected
            selected = currentScreens == "Community",

            onClick = {
                //call onNav with "community"
                onNavigate("Community")
            }
        )
    }


}





