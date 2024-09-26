package Lab9


import Lab9.LocationDetails.LocationDetailDestination
import Lab9.LocationDetails.LocationDetailScreen
import Lab9.LocationDetails.navigateToLocationDetailsScreen
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import Lab9.Locations.LocationDestination
import Lab9.Locations.LocationScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import kotlinx.serialization.Serializable


@Serializable
data object LocationNestNav

fun NavGraphBuilder.locationGraph(
    navController: NavHostController
) {
    navigation<LocationNestNav>(
        startDestination = LocationDestination
    ) {
        LocationScreen(
            onCharacterClick = { id -> navController.navigateToLocationDetailsScreen(
                LocationDetailDestination(id)
            ) },
            onBackToLogin = navController::navigateUp
        )
        LocationDetailScreen(navController)
    }
}

@Composable
fun LocationNavGraph(paddingValues: PaddingValues) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = LocationNestNav,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        locationGraph(navController)
    }
}