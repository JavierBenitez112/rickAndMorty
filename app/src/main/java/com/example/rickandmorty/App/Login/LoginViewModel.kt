package com.example.rickandmorty.App.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickandmorty.App.Login.data.DataStoreUserPrefs
import com.example.rickandmorty.App.Login.data.LoginStatus
import com.example.rickandmorty.dataStore
import com.example.rickandmorty.domain.repository.UserPreferences
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userPreferences: UserPreferences
) : ViewModel() {

    private val _state = MutableStateFlow(DataStoreLoginState())
    val state = _state.asStateFlow()

    val authStatus = userPreferences.authStatus()
        .onStart {
            delay(2000)
        }
        .map { isLoggedIn ->
            if (isLoggedIn) {
                LoginStatus.Authenticated
            } else {
                LoginStatus.NonAuthenticated
            }
        }
        .catch { error ->
            println(error)
            LoginStatus.NonAuthenticated
        }
        .onEach { item -> println(item) }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            LoginStatus.Loading
        )

    val userNameState = userPreferences
        .getUserName()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            null
        )

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.UserNameChanged -> {
                _state.update {
                    it.copy(name = event.userName)
                }
            }
            LoginEvent.DeleteName -> { deleteUserName() }
            LoginEvent.SaveName -> { saveUserName() }
        }
    }

    fun loginUser() {
        viewModelScope.launch {
            userPreferences.loginUser()

        }
    }

    fun logoutUser() {
        viewModelScope.launch {
            userPreferences.logoutUser()
        }
    }

    private fun saveUserName() {
        viewModelScope.launch {
            userPreferences.setName(_state.value.name)
        }
    }

    private fun deleteUserName() {
        viewModelScope.launch {
            userPreferences.setName("")
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                LoginViewModel(
                    userPreferences = DataStoreUserPrefs(application.dataStore)
                )
            }
        }
    }
}