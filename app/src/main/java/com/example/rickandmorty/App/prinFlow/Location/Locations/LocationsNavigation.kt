package com.example.rickandmorty.App.prinFlow.Location.Locations
import com.example.rickandmorty.App.prinFlow.Character.Lista.CharacterDestination
import com.example.rickandmorty.App.prinFlow.Character.Lista.CharacterRoute
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object LocationDestination

fun NavGraphBuilder.LocationListScreen(
    onLocationClick: (Int) -> Unit
) {
    composable<LocationDestination> {
        LocationListRoute(onLocationClick = onLocationClick)
    }
}