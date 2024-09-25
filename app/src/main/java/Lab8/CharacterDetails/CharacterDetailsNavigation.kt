package Lab8.CharacterDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
data class DetailDestination(
    val ID: Int
)

fun NavController.navigateToCharacterDetailsScreen(
    destination: DetailDestination,
    navOptions: NavOptions? = null
) {
    this.navigate("characterDetails/${destination.ID}", navOptions)
}

fun NavGraphBuilder.DetailScreen(
    navController: NavHostController
) {
    composable("characterDetails/{ID}") { backStackEntry ->
        val id = backStackEntry.arguments?.getString("ID")?.toIntOrNull() ?: return@composable
        CharacterDetailRoute(
            characterId = id,
            navController = navController
        )
    }
}