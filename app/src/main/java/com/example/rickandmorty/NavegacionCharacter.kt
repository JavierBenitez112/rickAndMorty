package com.example.rickandmorty


import Lab8.CharacterDetails.DetailDestination
import Lab8.CharacterDetails.DetailScreen
import Lab8.CharacterDetails.navigateToCharacterDetailsScreen
import Lab8.Characters.CharacterDestination
import Lab8.Characters.LoginDestination
import Lab8.Characters.characterScreen
import Lab8.Characters.loginScreen
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable


@Serializable
data object CharacterNavGraphDestination

fun NavController.navigateToCharacterNavGraph(
    navOptions: NavOptions? = null
) {
    this.navigate(CharacterNavGraphDestination, navOptions)
}

public fun NavGraphBuilder.characterNavGraph(
    paddingValues: PaddingValues,
    onLoginClick: () -> Unit,
    onCharacterClick: (String) -> Unit,
    onBackToLogin: () -> Unit
): Unit {
    composable<CharacterNavGraphDestination> {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = LoginDestination,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            loginScreen(
                onLoginClick = {
                    navController.navigate(CharacterDestination) {
                        popUpTo(LoginDestination) { inclusive = true }
                    }
                }
            )
            characterScreen(
                onCharacterClick = { characterId ->
                    navController.navigateToCharacterDetailsScreen(
                        destination = DetailDestination(
                            ID = characterId
                        )
                    )
                },
                onBackToLogin = {
                    navController.navigate(LoginDestination) {
                        popUpTo(CharacterDestination) { inclusive = true }
                    }
                }
            )
            DetailScreen(navController = navController)
        }
    }
}