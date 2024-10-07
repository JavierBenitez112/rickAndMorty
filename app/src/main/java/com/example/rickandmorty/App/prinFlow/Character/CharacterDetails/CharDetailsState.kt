package com.example.rickandmorty.App.prinFlow.Character.CharacterDetails

import com.example.rickandmorty.Datos.CharactersInfo.Character
import com.example.rickandmorty.Datos.CharactersInfo.CharacterDb

data class CharacterData(
    val data: Character? = null,
    val IsLoading: Boolean = false,
    val hasError: Boolean = false
)