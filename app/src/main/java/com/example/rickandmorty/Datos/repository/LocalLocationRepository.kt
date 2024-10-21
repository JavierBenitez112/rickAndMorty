package com.example.rickandmorty.Datos.repository

import com.example.rickandmorty.Datos.dao.CharacterDao
import com.example.rickandmorty.Datos.entity.mapToEntity
import com.example.rickandmorty.Datos.entity.mapToModel
import com.example.rickandmorty.Datos.localDb.sourceDb.CharacterDb
import com.example.rickandmorty.Datos.model.Characters
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.delay

class LocalCharacterRepository(
    private val characterDao: CharacterDao
) : CharacterRepository {

    override suspend fun getCharacters(): List<Characters> {
        val lCharacters = characterDao.getAllCharacters()
        return lCharacters.map { localCharacter ->
            localCharacter.mapToModel()
        }
    }

    override suspend fun getCharacterById(id: Int): Characters {
        val localCharacter = characterDao.getCharacter(id)
        return localCharacter.mapToModel()
    }

    override suspend fun populateLocalCharacterDatabase() {
        val remoteCharacters = CharacterDb().getAllCharacters()
        val localCharacters = remoteCharacters.map { remoteCharacter ->
            remoteCharacter.mapToEntity()
        }
        characterDao.insertAll(localCharacters)
    }
}