package com.teka.googlemapsproject.presentation.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource


@SuppressLint("MissingPermission")
fun getCurrentLocation(
    context: Context,
    fusedLocationProviderClient: FusedLocationProviderClient,
    onSuccess: (Double, Double) -> Unit,
    onFailure: (Exception) -> Unit,
    priority: Boolean = true
) {
    if (areLocationPermissionsGranted(context)) {
        val accuracy = if (priority) Priority.PRIORITY_HIGH_ACCURACY
        else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        fusedLocationProviderClient.getCurrentLocation(
            accuracy,
            CancellationTokenSource().token
        ).addOnSuccessListener { location ->
            location?.let {
                // If location is found, return latitude and longitude
                onSuccess(it.latitude, it.longitude)
            } ?: run {
                // If location is null, handle failure
                onFailure(Exception("Failed to retrieve location"))
            }
        }.addOnFailureListener { exception ->
            // If there is an error in retrieving the location
            onFailure(exception)
        }
    } else {
        onFailure(Exception("Location permissions not granted"))
    }
}

