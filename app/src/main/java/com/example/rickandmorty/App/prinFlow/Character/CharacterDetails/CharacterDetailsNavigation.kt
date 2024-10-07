package com.example.rickandmorty.App.prinFlow.Character.CharacterDetails

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
data class DetailDestination(
    val characterId: Int
)

fun NavController.navigateToCharacterDetailsScreen(
    characterId: Int,
    navOptions: NavOptions? = null
) {
    this.navigate(
        route = DetailDestination(characterId = characterId),
        navOptions = navOptions
    )
}

fun NavGraphBuilder.DetailScreen(
    onNavigateBack: () -> Unit
) {
    composable<DetailDestination> { backStackEntry ->
        val destination: DetailDestination = backStackEntry.toRoute()
        CharacterDetailRoute(
            id = destination.characterId,
            onNavigateBack = onNavigateBack
        )
    }
}
