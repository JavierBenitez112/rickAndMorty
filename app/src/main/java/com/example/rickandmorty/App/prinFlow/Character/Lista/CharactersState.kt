package com.example.rickandmorty.App.prinFlow.Character.Lista

import com.example.rickandmorty.Datos.model.Characters

data class CharactersState(
    val data: List<Characters> = emptyList(),
    val isLoading: Boolean = true,
    val hasError: Boolean = false
)