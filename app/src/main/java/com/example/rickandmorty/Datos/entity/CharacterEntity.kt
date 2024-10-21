package com.example.rickandmorty.Datos.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.rickandmorty.Datos.model.Characters

@Entity
data class CharacterEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val image: String
)

fun CharacterEntity.mapToModel(): Characters {
    return Characters(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}

fun Characters.mapToEntity(): CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        status = status,
        species = species,
        gender = gender,
        image = image
    )
}