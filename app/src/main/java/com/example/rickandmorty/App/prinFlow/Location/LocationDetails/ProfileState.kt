package com.example.rickandmorty.App.prinFlow.Location.LocationDetails

import Location

data class LocationProfileState(
    val data: Location? = null,
    val IsLoading: Boolean = false,
    val hasError: Boolean = false
)
