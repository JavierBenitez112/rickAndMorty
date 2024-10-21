package com.example.rickandmorty.Datos.repository

import LocationDb
import com.example.rickandmorty.Datos.dao.LocationDao
import com.example.rickandmorty.Datos.entity.mapToEntity
import com.example.rickandmorty.Datos.entity.mapToModel
import com.example.rickandmorty.Datos.model.Location
import com.example.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.delay

class LocalLocationRepository(
    private val locationDao: LocationDao
) {
    suspend fun getLocations(): List<Location> {
        val LLocation = locationDao.getAllLocations()

        return LLocation.map { localLocation ->
            localLocation.mapToModel()
        }
    }

    suspend fun getLocation(id: Int): Location {
        val localLocation = locationDao.getLocation(id)

        return localLocation.mapToModel()
    }

    suspend fun populateLocalLocationDatabase() {
        val remoteLocations = LocationDb().getAllLocations()
        val localLocation = remoteLocations.map { remoteLocation ->
            remoteLocation.mapToEntity()
        }
        locationDao.insertAll(localLocation)
    }
}