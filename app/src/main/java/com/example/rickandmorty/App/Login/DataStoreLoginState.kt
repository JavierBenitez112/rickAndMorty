package com.example.rickandmorty.App.Login

data class DataStoreLoginState(
    val name: String = "",
    val isLoading: Boolean = false,
    val Key: String = "",
    val value: String? = null
)