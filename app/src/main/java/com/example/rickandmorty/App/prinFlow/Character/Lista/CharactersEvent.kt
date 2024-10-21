package com.example.rickandmorty.App.prinFlow.Character.Lista

sealed interface CharactersEvent {
    data object ForceError: CharactersEvent
    data object RetryClick: CharactersEvent
    data object PopulateDatabase : CharactersEvent
}