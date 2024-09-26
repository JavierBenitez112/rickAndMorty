package Lab9.LocationDetails

import Lab8.CharacterDetails.CharacterDetailRoute
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable

@Serializable
data class LocationDetailDestination(
    val ID: Int
)

fun NavController.navigateToLocationDetailsScreen(
    destination: LocationDetailDestination,
    navOptions: NavOptions? = null
) {
    this.navigate("locationDetails/${destination.ID}", navOptions)
}

fun NavGraphBuilder.LocationDetailScreen(
    navController: NavHostController
) {
    composable("locationDetails/{ID}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("ID")?.toIntOrNull() ?: return@composable
        LocationDetailRoute(
            locationId = id,
            navController = navController
        )
    }
}