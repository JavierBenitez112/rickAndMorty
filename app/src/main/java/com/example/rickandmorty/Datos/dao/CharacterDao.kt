package com.example.rickandmorty.Datos.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty.Datos.entity.CharacterEntity

@Dao
interface CharacterDao {
    @Query("SELECT * FROM CharacterEntity")
    suspend fun getAllCharacters() : List<CharacterEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<CharacterEntity>)

    @Query ("SELECT * FROM CharacterEntity WHERE id = :id")
    suspend fun getCharacter(id: Int) : CharacterEntity
}