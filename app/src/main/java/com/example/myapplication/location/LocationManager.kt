package com.example.myapplication.location

import android.content.Context
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource

// LocationManager - Handles GPS/Location sensor
// Uses Fused Location Provider for efficient location updates
class LocationManager(context: Context) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    // Get current location asynchronously
    // Uses HIGH_ACCURACY priority for best location data
    fun getCurrentLocation(
        onSuccess: (latitude: Double, longitude: Double) -> Unit,
        onError: (String) -> Unit
    ) {
        try {
            val cancellationTokenSource = CancellationTokenSource()

            fusedLocationClient.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                cancellationTokenSource.token
            ).addOnSuccessListener { location: Location? ->
                if (location != null) {
                    // Location retrieved successfully
                    onSuccess(location.latitude, location.longitude)
                } else {
                    // Location is null (might be first request)
                    onError("Location is null - trying again")
                }
            }.addOnFailureListener { exception ->
                // Handle failure
                onError(exception.message ?: "Unknown error getting location")
            }
        } catch (e: SecurityException) {
            // Handle permission denied
            onError("Permission denied: ${e.message}")
        }
    }
}
