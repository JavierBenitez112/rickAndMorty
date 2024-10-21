package com.example.rickandmorty.App.Login.data

interface LoginStatus {
    data object Loading: LoginStatus
    data object Authenticated: LoginStatus
    data object NonAuthenticated: LoginStatus
}