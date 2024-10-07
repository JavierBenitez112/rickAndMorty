package com.example.rickandmorty.App.prinFlow.Character

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.example.rickandmorty.App.prinFlow.Character.CharacterDetails.DetailScreen
import com.example.rickandmorty.App.prinFlow.Character.CharacterDetails.navigateToCharacterDetailsScreen
import com.example.rickandmorty.App.prinFlow.Character.Lista.CharacterDestination
import com.example.rickandmorty.App.prinFlow.Character.Lista.characterScreen
import kotlinx.serialization.Serializable

@Serializable
data object CharacterNavGraph

fun NavGraphBuilder.characterGraph(
    navController: NavController
) {
    navigation<CharacterNavGraph>(
        startDestination = CharacterDestination
    ) {
        characterScreen(
            onCharacterClick = navController::navigateToCharacterDetailsScreen
        )
        DetailScreen(
            onNavigateBack = navController::navigateUp
        )
    }
}