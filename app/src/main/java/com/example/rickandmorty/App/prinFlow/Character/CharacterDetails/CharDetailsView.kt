package com.example.rickandmorty.App.prinFlow.Character.CharacterDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.rickandmorty.Datos.CharactersInfo.CharacterDb
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CharDetailsViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val characterDb = CharacterDb()
    private val characterProfile = savedStateHandle.toRoute<DetailDestination>()
    private val _uiState: MutableStateFlow<CharacterData> = MutableStateFlow(
        CharacterData()
    )
    val uiState = _uiState.asStateFlow()

    fun getCharacterData(){
        viewModelScope.launch {

            _uiState.update { state ->
                state.copy(
                    IsLoading = true
                )
            }

            delay(2000)


            val character = characterDb.getCharacterById(
                characterProfile.characterId
            )


            _uiState.update { state ->
                state.copy(
                    data = character,
                    IsLoading = false,
                )
            }



        }
    }
    fun onLoadingClick() {
        _uiState.update { state ->
            state.copy(
                IsLoading = false,
                hasError = true
            )
        }
    }
    fun onRetryClick() {
        _uiState.update { state ->
            state.copy(
                IsLoading = true,
                hasError = false
            )
        }
        getCharacterData()
    }
}