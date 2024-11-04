package com.example.rickandmorty.Datos.repository

import com.example.rickandmorty.Datos.dao.CharacterDao
import com.example.rickandmorty.Datos.entity.mapToEntity
import com.example.rickandmorty.Datos.entity.mapToModel
import com.example.rickandmorty.Datos.localDb.sourceDb.CharacterDb
import com.example.rickandmorty.Datos.model.Characters
import com.example.rickandmorty.Datos.network.KtorRickApi
import com.example.rickandmorty.Datos.network.dto.mapToCharacterModel
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.delay

class LocalCharacterRepository(
    private val characterDao: CharacterDao,
    private val api: KtorRickApi
) {

    suspend fun getCharacters(): List<Characters> {
        val LCharacters = characterDao.getAllCharacters()

        return LCharacters.map { localCharacter ->
            localCharacter.mapToModel()
        }
    }

    suspend fun getCharacter(id: Int): Characters {
        val localCharacter = characterDao.getCharacter(id)

        return localCharacter.mapToModel()
    }

    suspend fun populateLocalCharacterDatabase() {
        val remoteCharacters = CharacterDb().getAllCharacters()
        val localCharacters = remoteCharacters.map { remoteCharacter ->
            remoteCharacter.mapToEntity()
        }
        characterDao.insertAll(localCharacters)
    }


    suspend fun populateOnlineCharacterDatabase() {
        val response = api.getAllCharacters()
        val onlineCharacters = response.map { characterDto ->
            characterDto.mapToCharacterModel()
        }
        characterDao.insertAll(onlineCharacters)
    }



}