package com.example.rickandmorty.App.prinFlow

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable
import kotlin.reflect.KFunction0

@Serializable
data object MainNavigationGraph

fun NavController.navigateToMainGraph(navOptions: NavOptions? = null) {
    this.navigate(MainNavigationGraph, navOptions)
}

fun NavGraphBuilder.mainNavigationGraph(
    onLogOutClick: KFunction0<Unit>,
) {
    composable<MainNavigationGraph> {
        val nestedNavController = rememberNavController()
        MainFlowScreen(
            navController = nestedNavController,
            onLogOutClick = onLogOutClick
        )
    }
}