package com.example.rickandmorty.Datos.repository

import LocationDb
import com.example.rickandmorty.Datos.model.Location
import com.example.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.delay

class LocalLocationRepository: LocationRepository {
    private val locationDb = LocationDb()

    override suspend fun getLocations(): List<Location> {
        delay(4000)
        return locationDb.getAllLocations()
    }

    override suspend fun getLocationById(id: Int): Location {
        delay(2000)
        return locationDb.getLocationById(id)
    }
}