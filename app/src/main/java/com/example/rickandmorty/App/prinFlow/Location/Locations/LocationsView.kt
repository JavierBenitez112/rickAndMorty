package com.example.rickandmorty.App.prinFlow.Location.Locations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.rickandmorty.Datos.di.Dependencies
import com.example.rickandmorty.Datos.repository.LocalLocationRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

import kotlinx.coroutines.launch


class LocationsViewModel(
    private val locationRepository: LocalLocationRepository
) : ViewModel() {

    private var getDataJob: Job? = null
    private val _state = MutableStateFlow(LocationsState())
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
            LocationListEvent.PopulateDatabase -> {
                populateDatabase()
            }
        }
    }

    private fun getLocations() {
        getDataJob = viewModelScope.launch {
            locationRepository.populateLocalLocationDatabase()

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

    private fun populateDatabase() {
        viewModelScope.launch {
            _state.update { state ->
                state.copy(
                    isLoading = true,
                    hasError = false
                )
            }

            try {
                locationRepository.populateLocalLocationDatabase()
                getLocations()
            } catch (e: Exception) {
                _state.update { state ->
                    state.copy(
                        isLoading = false,
                        hasError = true
                    )
                }
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val db = Dependencies.provideDatabase(application)
                LocationsViewModel(
                    locationRepository = LocalLocationRepository(
                        locationDao = db.locationDao()
                    )
                )
            }
        }
    }
}