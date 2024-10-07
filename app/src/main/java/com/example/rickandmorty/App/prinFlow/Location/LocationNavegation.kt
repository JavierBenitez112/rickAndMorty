package com.example.rickandmorty.App.prinFlow.Location



import com.example.rickandmorty.App.prinFlow.Location.Locations.LocationDestination
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.navigation
import com.example.rickandmorty.App.prinFlow.Location.LocationDetails.locationProfileScreen
import com.example.rickandmorty.App.prinFlow.Location.LocationDetails.navigateToLocationProfileScreen
import com.example.rickandmorty.App.prinFlow.Location.Locations.LocationListScreen
import kotlinx.serialization.Serializable


@Serializable
data object LocationsNavGraph

fun NavController.navigateToLocationsGraph(navOptions: NavOptions? = null) {
    this.navigate(LocationsNavGraph, navOptions)
}

fun NavGraphBuilder.locationsGraph(
    navController: NavController
) {
    navigation<LocationsNavGraph>(
        startDestination = LocationDestination
    ) {
        LocationListScreen(
            onLocationClick = navController::navigateToLocationProfileScreen
        )
        locationProfileScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}