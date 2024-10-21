package com.example.rickandmorty.Datos.localDb

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty.Datos.dao.CharacterDao
import com.example.rickandmorty.Datos.dao.LocationDao
import com.example.rickandmorty.Datos.entity.CharacterEntity
import com.example.rickandmorty.Datos.entity.LocationEntity

@Database(entities = [CharacterEntity::class, LocationEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun locationDao(): LocationDao
}