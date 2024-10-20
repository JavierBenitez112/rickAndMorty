package com.example.rickandmorty.App.prinFlow.Location.Locations

import com.example.rickandmorty.Datos.model.Location


data class LocationsState(
    val data: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)