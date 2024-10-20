package com.example.rickandmorty.App.prinFlow.Character.Lista

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickandmorty.Datos.repository.LocalCharacterRepository
import com.example.rickandmorty.domain.repository.CharacterRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CharacterViewModel(
    private val characterRepository: CharacterRepository
) : ViewModel() {

    private var getDataJob: Job? = null
    private val _state = MutableStateFlow(CharactersState())
    val state = _state.asStateFlow()

    init {
        getCharacters()
    }

    fun onEvent(event: CharactersEvent) {
        when (event) {
            CharactersEvent.ForceError -> {
                getDataJob?.cancel()
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }
            CharactersEvent.RetryClick -> {
                getCharacters()
            }
        }
    }

    private fun getCharacters() {
        getDataJob = viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            val characters = characterRepository.getCharacters()

            _state.update { state ->
                state.copy(
                    isLoading = false,
                    data = characters
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CharacterViewModel(
                    characterRepository = LocalCharacterRepository()
                )
            }
        }
    }
}