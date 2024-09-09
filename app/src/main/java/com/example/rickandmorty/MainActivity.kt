package com.example.rickandmorty

import Lab8.CharacterDetails.characterDetailScreen
import Lab8.Characters.CharacterDestination
import Lab8.Characters.LoginDestination
import Lab8.Characters.characterScreen
import Lab8.Characters.loginScreen
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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
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
                                navController.navigate("characterDetail/$characterId")
                            },
                            onBackToLogin = {
                                finish()
                            }
                        )
                        characterDetailScreen(
                            onNavigateBack = {
                                navController.navigateUp()
                            }
                        )
                    }
                }
            }
        }
    }
}