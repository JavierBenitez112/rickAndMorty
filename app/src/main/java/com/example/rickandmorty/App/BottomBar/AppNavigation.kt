package com.example.rickandmorty.App.BottomBar

import Lab8.Characters.LoginDestination
import Lab8.Characters.loginScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.App.Login.LoginViewModel
import com.example.rickandmorty.App.Login.data.LoginStatus
import com.example.rickandmorty.App.prinFlow.MainNavigationGraph
import com.example.rickandmorty.App.prinFlow.mainNavigationGraph
import kotlinx.serialization.Serializable

@Serializable
data object AppNavigationGraph

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    viewModel: LoginViewModel = viewModel(factory = LoginViewModel.Factory)

) {
    val authStatus by viewModel.authStatus.collectAsStateWithLifecycle()

    NavHost(
        navController = navController,
        startDestination = AppNavigationGraph,
        modifier = modifier
    ) {
        composable<AppNavigationGraph> {

        }
        loginScreen(
            onLoginClick = viewModel::loginUser
        )
        mainNavigationGraph(
            onLogOutClick = viewModel::logoutUser
        )
    }
    LaunchedEffect(authStatus) {
        when (authStatus) {
            LoginStatus.Authenticated -> {
                navController.navigate(MainNavigationGraph) {
                    popUpTo(LoginDestination) {
                        inclusive = true
                    }
                }
            }
            LoginStatus.NonAuthenticated -> {
                navController.navigate(LoginDestination) {
                    popUpTo(0)
                }
            }
            LoginStatus.Loading -> {}
        }
    }
}
