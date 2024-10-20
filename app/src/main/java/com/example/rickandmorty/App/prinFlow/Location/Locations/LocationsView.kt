package com.example.rickandmorty.App.prinFlow.Location.Locations

import LocationDb
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickandmorty.Datos.repository.LocalLocationRepository
import com.example.rickandmorty.domain.repository.LocationRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch

class LocationsViewModel(
    private val locationRepository: LocationRepository
): ViewModel() {
    private var getDataJob: Job? = null
    private var _state = MutableStateFlow(LocationsState())
    val state = _state.asStateFlow()

    init {
        getLocations()
    }

    fun onEvent(event: LocationListEvent) {
        when (event) {
            LocationListEvent.ForceError -> {
                getDataJob?.cancel()
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }
            LocationListEvent.RetryClick -> {
                getLocations()
            }
        }
    }

    private fun getLocations() {
        getDataJob = viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            val locations = locationRepository.getLocations()

            _state.update { state ->
                state.copy(
                    isLoading = false,
                    data = locations
                )
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                LocationsViewModel(
                    locationRepository = LocalLocationRepository()
                )
            }
        }
    }

}