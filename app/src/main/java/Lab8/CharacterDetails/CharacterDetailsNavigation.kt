package Lab8.CharacterDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDetailDestination(
    val characterId: Int
)

fun NavController.navigateToCharacterDetailScreen(
    destination: CharacterDetailDestination,
    navOptions: NavOptions? = null
){
    this.navigate(destination, navOptions)
}

fun NavGraphBuilder.characterDetailScreen(onNavigateBack: () -> Unit){
    composable<CharacterDetailDestination> { backStackEntry ->
        val destination: CharacterDetailDestination = backStackEntry.toRoute()
        CharacterDetailRoute(
            characterId = destination.characterId,
            onNavigateBack = onNavigateBack
        )
    }
}