package com.example.rickandmorty.Datos.di

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty.Datos.localDb.AppDatabase

object Dependencies {
    private var database: AppDatabase? = null

    private fun buildDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "uvg.db"
        ).build()
    }

    fun provideDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: buildDatabase(context).also { database = it }
        }
    }
}