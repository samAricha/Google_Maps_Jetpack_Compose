package com.teka.googlemapsproject.presentation.location

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.android.gms.location.LocationServices

@Composable
fun LocationComposable(context: Context) {
    val fusedLocationProviderClient = remember {
        LocationServices.getFusedLocationProviderClient(context)
    }
    
    var locationText by remember { mutableStateOf("Location not available") }

    // Request location permissions
    RequestLocationPermission(
        onPermissionGranted = {
            getCurrentLocation(
                context = context,
                fusedLocationProviderClient = fusedLocationProviderClient,
                onSuccess = { latitude, longitude ->
                    locationText = "Lat: $latitude, Long: $longitude"
                },
                onFailure = { exception ->
                    // Handle failure in getting location
                    locationText = "Failed to get location"
                    Toast.makeText(context, exception.message, Toast.LENGTH_SHORT).show()
                }
            )



           /* getLastUserLocation(
                context = context,
                fusedLocationProviderClient = fusedLocationProviderClient,
                onSuccess = { latitude, longitude ->
                    locationText = "Lat: $latitude, Long: $longitude"
                },
                onFailure = {
                    locationText = "Failed to get location"
                    Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                }
            ) */
        },
        onPermissionDenied = {
            locationText = "Location permission denied."
        },
        onPermissionsRevoked = {
            locationText = "Location permissions revoked."
        }
    )

    // Composable UI
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = locationText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}





