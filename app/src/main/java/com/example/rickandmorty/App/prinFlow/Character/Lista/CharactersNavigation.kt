package com.example.rickandmorty.App.prinFlow.Character.Lista

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import kotlinx.serialization.Serializable
@Serializable
data object CharacterDestination

fun NavGraphBuilder.characterScreen(
    onCharacterClick: (Int) -> Unit,
) {
    composable<CharacterDestination> {
        CharacterRoute(onCharacterClick = onCharacterClick)
    }
}