package com.example.rickandmorty.App.prinFlow.Character.CharacterDetails

import com.example.rickandmorty.Datos.model.Characters

data class CharacterData(
    val data: Characters? = null,
    val IsLoading: Boolean = false,
    val hasError: Boolean = false
)