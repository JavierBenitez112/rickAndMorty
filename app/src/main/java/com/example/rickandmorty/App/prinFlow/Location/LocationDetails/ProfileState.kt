package com.example.rickandmorty.App.prinFlow.Location.LocationDetails

import com.example.rickandmorty.Datos.model.Location


data class LocationProfileState(
    val data: Location? = null,
    val IsLoading: Boolean = false,
    val hasError: Boolean = false
)
