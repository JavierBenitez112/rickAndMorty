package com.example.rickandmorty.App.prinFlow.Location.Locations


import Location
import LocationDb
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.App.ErrorScreen
import com.example.rickandmorty.App.LoadingScreen
import com.example.rickandmorty.App.prinFlow.Location.LocationDetails.LocationProfileViewModel
import com.example.rickandmorty.App.theme.RickAndMortyTheme


@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationsViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getListLocations()
    }

    val locationsState by viewModel.uiState.collectAsStateWithLifecycle()

    LocationListScreen(
        locations = locationsState.data,
        isLoading = locationsState.isLoading,
        hasError = locationsState.hasError,
        onLoadingClick = { viewModel.onLoadingClick() },
        onRetryClick = { viewModel.onRetryClick() },
        onLocationClick = onLocationClick,
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LocationListScreen(
    locations: List<Location>,
    isLoading: Boolean,
    hasError: Boolean,
    onLoadingClick: () -> Unit,
    onRetryClick: () -> Unit,
    onLocationClick: (Int) -> Unit,
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
                    errorMessage = "Error al cargar la lista de ubicaciones"

                )
            }

            else -> {
                LazyColumn {
                    items(locations) { item ->
                        LocationItem(
                            location = item,
                            modifier = Modifier.clickable { onLocationClick(item.id) }
                        )
                    }
                }
            }
        }
    }
}
@Composable
private fun LocationItem(
    location: Location,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier
        .padding(16.dp)
    ) {
        Text(text = location.name)
        Text(
            text = location.type,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewLocationListScreen() {
    RickAndMortyTheme {
        Surface {
            val db = LocationDb()
            LocationListScreen(
                locations = db.getAllLocations().take(6),
                isLoading = false,
                hasError = false,
                onLoadingClick = {},
                onRetryClick = {},
                onLocationClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}