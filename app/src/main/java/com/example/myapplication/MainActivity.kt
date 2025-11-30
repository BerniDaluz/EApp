package com.example.myapplication

// Android core
import android.os.Bundle
import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

// Compose runtime
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect

// Layout
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier

// Custom theme
import com.example.myapplication.ui.theme.EnvironmentalMonitoringTheme

// Three screens
import com.example.myapplication.ui.screens.DashboardScreen
import com.example.myapplication.ui.screens.MapScreen
import com.example.myapplication.ui.screens.CommunityScreen

// ViewModel state management
import com.example.myapplication.viewmodel.AppViewModel

// Import bottom navigation bar
import com.example.myapplication.ui.components.BottomNavigationBar

// Import location manager for GPS
import com.example.myapplication.sensors.LocationManager
import com.example.myapplication.sensors.TemperatureManager

// Main screen of app
// Beginning of the app
class MainActivity : ComponentActivity() {
    private lateinit var locationManager: LocationManager
    private lateinit var temperatureManager : TemperatureManager
    private var viewModelInstance: AppViewModel? = null
    private val LOCATION_PERMISSION_REQUEST_CODE = 100

    // Function runs when app starts
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize location manager
        locationManager = LocationManager(this)

        temperatureManager = TemperatureManager(this)

        // Check if location permission is granted
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Permission not granted, request it
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        } else {
            // Permission already granted, get location
            getLocation()
        }

        // Display Compose UI
        setContent {
            // Dark theme with cyan colors
            EnvironmentalMonitoringTheme {
                // Main dashboard screen
                MainAppContent { viewModel ->
                    viewModelInstance = viewModel
                    getLocation()
                    startTemperatureListening()
                }
            }
        }
    }

    // Get location from GPS sensor
    private fun getLocation() {
        locationManager.getCurrentLocation(
            onSuccess = { latitude, longitude ->
                // Update ViewModel with location
                viewModelInstance?.updateLocation(latitude, longitude)
            },
            onError = { error ->
                // Handle error - UPDATED to use updateLocationError()
                viewModelInstance?.updateLocationError(error)
            }
        )
    }
    private fun startTemperatureListening(){
        temperatureManager.startListening(
            onChanged = {
                temp ->
                viewModelInstance?.updateTemperature(temp)
                viewModelInstance?.addTemperatureHistory(temp)
            },
            onError = { error ->
                viewModelInstance?.setError("Temperature sensor error: $error" )
            }
        )
    }

    // Handle permission request result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, get location
                getLocation()
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        temperatureManager.stopListening()
    }
}

// Main content of the app
// Creates ViewModel for state management
// Sets Scaffold layout
// Displays current screen
// Shows bottom navigation bar
@Composable
fun MainAppContent(onViewModelReady: (AppViewModel) -> Unit) {
    // ViewModel - manages screens and location
    val viewModel = remember { AppViewModel() }

    // Use LaunchedEffect for side effects (calling the callback)
    LaunchedEffect(viewModel) {
        onViewModelReady(viewModel)
    }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                currentScreen = viewModel.currentScreen,
                onNavigate = { screenName ->
                    // On click, navigate to that screen
                    viewModel.navigateTo(screenName)
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            // Screen navigation
            when (viewModel.currentScreen) {
                "Dashboard" -> {
                    DashboardScreen(viewModel = viewModel)
                }
                "Map" -> {
                    MapScreen(viewModel = viewModel)
                }
                "Community" -> {
                    CommunityScreen()
                }
                else -> {
                    DashboardScreen(viewModel = viewModel)
                }
            }
        }
    }
}
