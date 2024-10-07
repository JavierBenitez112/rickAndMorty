package com.example.rickandmorty.App.prinFlow.Character.Lista

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty.Datos.CharactersInfo.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class CharacterViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val CharacterDb = CharacterDb()
    private val _uiState: MutableStateFlow<CharactersState> = MutableStateFlow(
        CharactersState()
    )
    val uiState = _uiState.asStateFlow()

    fun getCharacters(){
        viewModelScope.launch {

            _uiState.update { state ->
                state.copy(
                    isLoading = true
                )
            }


            delay(4000)


            val Characters = CharacterDb.getAllCharacters()


            _uiState.update { state ->
                state.copy(
                    data = Characters,
                    isLoading = false,
                )
            }
        }
    }

    fun onLoadingClick() {
        _uiState.update { state ->
            state.copy(
                isLoading = false,
                hasError = true
            )
        }
    }
    fun onRetryClick() {
        _uiState.update { state ->
            state.copy(
                isLoading = true,
                hasError = false
            )
        }
        getCharacters()
    }
}