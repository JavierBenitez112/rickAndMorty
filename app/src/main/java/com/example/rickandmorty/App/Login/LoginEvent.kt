package com.example.rickandmorty.App.Login

sealed interface LoginEvent {
    data class UserNameChanged(val userName: String) : LoginEvent
    data object SaveName: LoginEvent
    data object  DeleteName: LoginEvent
}