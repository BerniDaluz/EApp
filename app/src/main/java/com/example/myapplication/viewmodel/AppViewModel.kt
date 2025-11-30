package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

// AppViewModel -> Manages state of the app
// Keeps track of which screen is currently being displayed
// Manages data being shared between screens
// Manages GPS location data

class AppViewModel : ViewModel() {
    // Tracks which screen is being displayed
    var currentScreen by mutableStateOf("Dashboard")
        private set // Only this view model can change it

    // Stores currently selected environment reading
    var selectedReading by mutableStateOf<String?>(null)
        private set

    // Tracks if data is currently being loaded
    var isLoading by mutableStateOf(false)
        private set

    // Stores an error message
    var errorMessage by mutableStateOf<String?>(null)
        private set

    // GPS Location data
    var userLatitude by mutableStateOf(0.0)
        private set

    var userLongitude by mutableStateOf(0.0)
        private set

    var locationError by mutableStateOf<String?>(null)
        private set

    var hasLocation by mutableStateOf(false)
        private set

    // Changes the current screen
    fun navigateTo(screenName: String) {
        // Update currentScreen to new screen name
        currentScreen = screenName

        // Clear any error message when navigating
        errorMessage = null
    }

    // Selects a reading (stores which one was clicked)
    fun selectReading(readingId: String) {
        // Stores selected reading id
        selectedReading = readingId
    }

    // Deselects the current reading
    fun clearSelectedReading() {
        // Set selected reading back to null
        selectedReading = null
    }

    // Updates loading state
    fun updateLoading(loading: Boolean) {
        // Updates isLoading state
        isLoading = loading
    }

    // Shows an error message to the user
    fun setError(message: String?) {
        // Update error message
        errorMessage = message
    }

    // Update location from GPS sensor
    fun updateLocation(latitude: Double, longitude: Double) {
        // Store GPS coordinates
        userLatitude = latitude
        userLongitude = longitude
        locationError = null
        hasLocation = true
    }

    // Update location error message
    // RENAMED from setLocationError to avoid JVM signature clash
    fun updateLocationError(error: String) {
        // Store error message
        locationError = error
        hasLocation = false
    }
}
