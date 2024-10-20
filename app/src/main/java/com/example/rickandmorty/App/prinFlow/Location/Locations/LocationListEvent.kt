package com.example.rickandmorty.App.prinFlow.Location.Locations

sealed interface LocationListEvent {
    data object ForceError: LocationListEvent
    data object RetryClick: LocationListEvent
}