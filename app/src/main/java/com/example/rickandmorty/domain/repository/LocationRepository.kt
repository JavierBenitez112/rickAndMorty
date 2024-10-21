package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.Datos.model.Location

interface LocationRepository {
    suspend fun getLocations(): List<Location>
    suspend fun getLocationById(id: Int): Location
    suspend fun populateLocalLocationDatabase()
}