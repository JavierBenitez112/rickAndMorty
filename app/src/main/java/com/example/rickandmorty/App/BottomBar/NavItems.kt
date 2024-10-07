package com.example.rickandmorty.App.BottomBar

import com.example.rickandmorty.App.prinFlow.Profile.ProfileDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rickandmorty.App.prinFlow.Character.CharacterNavGraph
import com.example.rickandmorty.App.prinFlow.Character.Lista.CharacterDestination
import com.example.rickandmorty.App.prinFlow.Location.Locations.LocationDestination
import com.example.rickandmorty.App.prinFlow.Location.LocationsNavGraph


data class NavItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Any, // Utilizamos Any ya que debemos poner aquí nuestros Destinations
)

val navigationItems = listOf(
    NavItem(
        title = "Characters",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        destination = CharacterNavGraph
    ),
    NavItem(
        title = "Locations",
        selectedIcon = Icons.Filled.LocationOn,
        unselectedIcon = Icons.Outlined.LocationOn,
        destination = LocationsNavGraph
    ),
    NavItem(
        title = "Profile",
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        destination = ProfileDestination
    )
)

val topLevelDestinations = listOf(
    CharacterDestination::class,
    LocationDestination::class,
    ProfileDestination::class
)
