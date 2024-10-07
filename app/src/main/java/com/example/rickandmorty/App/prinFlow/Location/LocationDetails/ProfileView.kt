package com.example.rickandmorty.App.prinFlow.Location.LocationDetails

import LocationDb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LocationProfileViewModel(
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val locationDb = LocationDb()
    private val locationProfile = savedStateHandle.toRoute<LocationProfileDestination>()
    private val _uiState: MutableStateFlow<LocationProfileState> = MutableStateFlow(
        LocationProfileState()
    )
    val uiState = _uiState.asStateFlow()

    fun getLocationData() {
        viewModelScope.launch {
            _uiState.update { state ->
                state.copy(
                    IsLoading = true
                )
            }

            delay(2000)
            val location = locationDb.getLocationById(
                locationProfile.locationId
            )

            _uiState.update { state ->
                state.copy(
                    data = location,
                    IsLoading = false
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
        getLocationData()
    }

}