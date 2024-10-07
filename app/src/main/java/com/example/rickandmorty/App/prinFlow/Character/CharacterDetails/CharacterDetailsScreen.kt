package com.example.rickandmorty.App.prinFlow.Character.CharacterDetails

import android.content.res.Configuration
import com.example.rickandmorty.Datos.CharactersInfo.CharacterDb
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

import com.example.rickandmorty.Datos.CharactersInfo.Character
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rickandmorty.App.ErrorScreen
import com.example.rickandmorty.App.LoadingScreen
import com.example.rickandmorty.App.prinFlow.Location.LocationDetails.LocationProfileViewModel
import com.example.rickandmorty.App.theme.RickAndMortyTheme


@Composable
fun CharacterDetailRoute(
    id: Int,
    onNavigateBack: () -> Unit,
    viewModel: CharDetailsViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(id) {
        viewModel.getCharacterData()
    }

    CharacterDetailScreen(
        state = state,

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
fun CharacterDetailScreen(
    state: CharacterData,
    onNavigateBack: () -> Unit,
    onLoadingClick: () -> Unit,
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Detail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            CharacterDetailContent(
                character = state.data,
                isLoading = state.IsLoading,
                hasError = state.hasError,
                onRetryClick = onRetryClick,
                onLoadingClick = onLoadingClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
fun CharacterDetailContent(
    character: Character?,
    isLoading: Boolean,
    hasError: Boolean,
    onRetryClick: () -> Unit,
    onLoadingClick: () -> Unit,
    modifier: Modifier = Modifier
) {
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { onRetryClick() },
                contentAlignment = Alignment.Center
            ) {
                ErrorScreen(
                    onRetry = onRetryClick,
                    errorMessage = "Error al cargar los detalles del personaje"
                )
            }
        }
        else -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(128.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(character?.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = character?.name,
                        modifier = Modifier
                            .size(192.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Text(
                    text = character?.name ?: "",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "Species", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Status", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Gender", style = MaterialTheme.typography.bodyMedium)
                    }
                    Column {
                        Text(text = character?.species ?: "", style = MaterialTheme.typography.bodyMedium)
                        Text(text = character?.status ?: "", style = MaterialTheme.typography.bodyMedium)
                        Text(text = character?.gender ?: "", style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewCharacterProfileScreen() {
    RickAndMortyTheme {
        Surface {
            CharacterDetailScreen(
                state = CharacterData(
                    IsLoading = false,
                    hasError = false,
                    data = Character(
                        id = 2565,
                        name = "Rick",
                        status = "Alive",
                        species = "Human",
                        gender = "Male",
                        image = ""
                    )
                ),
                onNavigateBack = { },
                onLoadingClick = { },
                onRetryClick = { },
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}