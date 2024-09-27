package com.teka.googlemapsproject.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.teka.googlemapsproject.core.Constants.LOCATIONS
import com.teka.googlemapsproject.data.repository.LocationsRepositoryImpl
import com.teka.googlemapsproject.domain.repository.LocationsRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideLocationsRef() = Firebase.firestore.collection(LOCATIONS)

    @Provides
    fun provideLocationsRepository(
        locationsRef: CollectionReference
    ): LocationsRepository = LocationsRepositoryImpl(locationsRef)
}