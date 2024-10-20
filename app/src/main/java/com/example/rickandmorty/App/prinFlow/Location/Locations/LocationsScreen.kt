package com.example.rickandmorty.App.prinFlow.Location.Locations


import LocationDb
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmorty.App.comun.ErrorScreen
import com.example.rickandmorty.App.comun.LoadingScreen
import com.example.rickandmorty.App.theme.RickAndMortyTheme
import com.example.rickandmorty.Datos.model.Location


@Composable
fun LocationListRoute(
    onLocationClick: (Int) -> Unit,
    viewModel: LocationsViewModel = viewModel(factory = LocationsViewModel.Factory)
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    LocationListScreen(
        state = state,
        forceError = { viewModel.onEvent(LocationListEvent.ForceError) },
        onLocationClick = onLocationClick,
        onRetryClick = { viewModel.onEvent(LocationListEvent.RetryClick) },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun LocationListScreen(
    state: LocationsState,
    forceError: () -> Unit,
    onRetryClick: () -> Unit,
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable { forceError() },
                    contentAlignment = Alignment.Center
                ) {
                    LoadingScreen()
                }
            }

            state.hasError -> {
                ErrorScreen(
                    onRetry = onRetryClick,
                    errorMessage = "Error al cargar la lista de ubicaciones"

                )
            }

            else -> {
                LazyColumn {
                    items(state.data) { item ->
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
                state = LocationsState(
                    data = db.getAllLocations().take(6),
                    isLoading = false,
                    hasError = false
                ),
                forceError = {},
                onRetryClick = {},
                onLocationClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}