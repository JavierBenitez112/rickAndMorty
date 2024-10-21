package com.example.rickandmorty.App.Login.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.rickandmorty.domain.repository.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

class DataStoreUserPrefs(private val dataStore: DataStore<Preferences>
): UserPreferences {

    private val nameKey = stringPreferencesKey("name")
    private val loggedKey = booleanPreferencesKey("logged")

    override suspend fun loginUser() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = true
        }
    }

    override suspend fun logoutUser() {
        dataStore.edit { preferences ->
            preferences[loggedKey] = false
        }
    }

    override fun authStatus(): Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[loggedKey] ?: false
    }

    override suspend fun setName(name: String) {
        dataStore.edit { preferences ->
            preferences[nameKey] = name
        }
    }

    override suspend fun getValue(key: String): String? {
        val preferencesKey = when (key) {
            "name" -> nameKey
            else -> null
        }

        preferencesKey?.let {
            val preferences = dataStore.data.first()
            return preferences[preferencesKey]
        }

        return null
    }

    override fun getUserName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[nameKey] ?: "No name"
        }
    }
}