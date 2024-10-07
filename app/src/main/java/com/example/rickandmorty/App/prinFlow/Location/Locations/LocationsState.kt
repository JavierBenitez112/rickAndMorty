package com.example.rickandmorty.App.prinFlow.Location.Locations

import Location
import LocationDb

data class LocationsState(
    val data: List<Location> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)