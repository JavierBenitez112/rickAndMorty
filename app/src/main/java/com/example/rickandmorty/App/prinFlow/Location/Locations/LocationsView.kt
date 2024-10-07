package com.example.rickandmorty.App.prinFlow.Location.Locations

import Location
import LocationDb
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

class LocationsViewModel(
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val locationDb = LocationDb()
    private val _uiState: MutableStateFlow<LocationsState> = MutableStateFlow(
        LocationsState()
    )
    val uiState = _uiState.asStateFlow()

    fun getListLocations(){
        viewModelScope.launch {

            _uiState.update { state ->
                state.copy(
                    isLoading = true
                )
            }


            delay(4000)


            val locations = locationDb.getAllLocations()


            _uiState.update { state ->
                state.copy(
                    data = locations,
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
        getListLocations()
    }
}