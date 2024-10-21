package com.example.rickandmorty.domain.repository

import com.example.rickandmorty.Datos.model.Characters

interface CharacterRepository {
    suspend fun getCharacters(): List<Characters>
    suspend fun getCharacterById(id: Int): Characters
    suspend fun populateLocalCharacterDatabase()
}