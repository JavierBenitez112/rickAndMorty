package com.example.rickandmorty.App.prinFlow.Character.Lista

import com.example.rickandmorty.Datos.CharactersInfo.Character

data class CharactersState(
    val data: List<Character> = emptyList(),
    val isLoading: Boolean = false,
    val hasError: Boolean = false
)