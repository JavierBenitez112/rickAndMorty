package com.example.rickandmorty


import Lab8.CharacterDetails.DetailDestination
import Lab8.CharacterDetails.DetailScreen

import Lab8.CharacterDetails.navigateToCharacterDetailsScreen
import Lab8.Characters.CharacterDestination
import Lab8.Characters.LoginDestination
import Lab8.Characters.characterScreen
import Lab8.Characters.loginScreen
import Lab9.BottomBar.BottomBarScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import android.util.Log
import androidx.navigation.compose.composable
import com.example.rickandmorty.ui.login.LoginScreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "main",
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        composable("main") {
                            LoginScreen(onLoginClick = {
                                navController.navigate("bottomBar") {
                                    popUpTo("main") { inclusive = true }
                                }
                            })
                        }
                        composable("bottomBar") {
                            BottomBarScreen(onLogoutClick = {
                                navController.navigate("main") {
                                    popUpTo(0)
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}