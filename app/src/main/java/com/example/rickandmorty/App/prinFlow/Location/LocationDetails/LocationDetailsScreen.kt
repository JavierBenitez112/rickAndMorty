package com.example.rickandmorty.App.prinFlow.Location.LocationDetails

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import Location
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.rickandmorty.App.theme.RickAndMortyTheme
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmorty.App.ErrorScreen
import com.example.rickandmorty.App.LoadingScreen

@Composable
fun LocationProfileRoute(
    onNavigateBack: () -> Unit,
    viewModel: LocationProfileViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    if (state.data == null && !state.IsLoading && !state.hasError) {
        viewModel.getLocationData()
    }

    LocationProfileScreen(
        state = state,
        onGetInfoClick = {
            viewModel.getLocationData()
        },
        onLoadingClick = {
            viewModel.onLoadingClick()
        },
        onRetryClick = {
            viewModel.onRetryClick()
        },
        onNavigateBack = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LocationProfileScreen(
    state: LocationProfileState,
    onGetInfoClick: () -> Unit,
    onLoadingClick: () -> Unit,
    onRetryClick: () -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        TopAppBar(
            title = {
                Text("Location Details")
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
        LocationProfileContent(
            location = state.data,
            isLoading = state.IsLoading,
            hasError = state.hasError,
            onGetInfoClick = onGetInfoClick,
            onLoadingClick = onLoadingClick,
            onRetryClick = onRetryClick,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun LocationProfileContent(
    location: Location?,
    isLoading: Boolean,
    hasError: Boolean,
    onGetInfoClick: () -> Unit,
    onLoadingClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when {
            isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onLoadingClick() },
                    contentAlignment = Alignment.Center
                ) {
                    LoadingScreen()
                }
            }

            hasError -> {
                ErrorScreen(
                    onRetry = onRetryClick,
                    errorMessage = "Error al cargar los detalles de la ubicacion"
                )
            }

            location == null -> {
                // No button, just loading screen
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { onGetInfoClick() },
                    contentAlignment = Alignment.Center
                ) {
                    LoadingScreen()
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 64.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = location.name,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LocationProfilePropItem(
                        title = "ID:",
                        value = location.id.toString(),
                        modifier = Modifier.fillMaxWidth()
                    )
                    LocationProfilePropItem(
                        title = "Type:",
                        value = location.type,
                        modifier = Modifier.fillMaxWidth()
                    )
                    LocationProfilePropItem(
                        title = "Dimensions:",
                        value = location.dimension,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Composable
private fun LocationProfilePropItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title)
        Text(text = value)
    }
}