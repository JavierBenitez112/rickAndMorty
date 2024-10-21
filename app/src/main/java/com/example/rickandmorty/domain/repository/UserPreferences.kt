package com.example.rickandmorty.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserPreferences {
    suspend fun loginUser()
    suspend fun logoutUser()
    suspend fun setName(name: String)
    suspend fun getValue(key: String): String?
    fun getUserName(): Flow<String>
    fun authStatus(): Flow<Boolean>


}