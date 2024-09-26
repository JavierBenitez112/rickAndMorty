package Lab9.BottomBar

import Lab9.LocationNestNav
import Lab9.Profile.ProfileDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.rickandmorty.CharacterNavGraphDestination


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val destination: Any
)

val BottomNavigationItems = listOf(
    BottomNavigationItem(
        title = "Home",
        unselectedIcon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        destination = CharacterNavGraphDestination
    ),
    BottomNavigationItem(
        title = "Location",
        unselectedIcon = Icons.Outlined.LocationOn,
        selectedIcon = Icons.Filled.LocationOn,
        destination = LocationNestNav
    ),
    BottomNavigationItem(
        title = "Profile",
        unselectedIcon = Icons.Outlined.Person,
        selectedIcon = Icons.Filled.Person,
        destination = ProfileDestination
    )
)