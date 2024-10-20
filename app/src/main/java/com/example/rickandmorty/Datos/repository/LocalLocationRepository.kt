package com.example.rickandmorty.Datos.repository

import com.example.rickandmorty.Datos.sourceDb.CharacterDb
import com.example.rickandmorty.Datos.model.Characters
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.delay

class LocalCharacterRepository: CharacterRepository {
    private val characterDb = CharacterDb()

    override suspend fun getCharacters(): List<Characters> {
        delay(2000L)
        return characterDb.getAllCharacters()
    }

    override suspend fun getCharacterById(id: Int): Characters {
        delay(2000L)
        return characterDb.getCharacterById(id)
    }
}