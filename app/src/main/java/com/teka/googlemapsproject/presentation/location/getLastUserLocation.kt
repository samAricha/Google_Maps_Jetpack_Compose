package com.teka.googlemapsproject.presentation.location

import android.annotation.SuppressLint
import android.content.Context
import com.google.android.gms.location.FusedLocationProviderClient

@SuppressLint("MissingPermission")
fun getLastUserLocation(
    context: Context,
    fusedLocationProviderClient: FusedLocationProviderClient,
    onSuccess: (Double, Double) -> Unit,
    onFailure: (Exception) -> Unit
) {
    if (areLocationPermissionsGranted(context = context)) {
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    onSuccess(it.latitude, it.longitude)
                }
            }
            .addOnFailureListener { exception ->
                onFailure(exception)
            }
    }
}
