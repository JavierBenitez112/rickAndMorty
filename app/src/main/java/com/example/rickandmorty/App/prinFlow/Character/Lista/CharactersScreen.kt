package com.example.rickandmorty.App.prinFlow.Character.Lista

import android.content.res.Configuration
import androidx.compose.foundation.background
import com.example.rickandmorty.Datos.CharactersInfo.CharacterDb
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmorty.App.theme.RickAndMortyTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import com.example.rickandmorty.Datos.CharactersInfo.Character
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.App.ErrorScreen
import com.example.rickandmorty.App.LoadingScreen


@Composable
fun CharacterRoute(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.getCharacters()
    }

    val charactersState by viewModel.uiState.collectAsStateWithLifecycle()

    CharacterScreen(
        characters = charactersState.data,
        isLoading = charactersState.isLoading,
        hasError = charactersState.hasError,
        onLoadingClick = { viewModel.onLoadingClick() },
        onCharacterClick = onCharacterClick,
        onRetryClick = { viewModel.onRetryClick() },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun CharacterScreen(
    characters: List<Character>,
    isLoading: Boolean,
    hasError: Boolean,
    onLoadingClick: () -> Unit,
    onCharacterClick: (Int) -> Unit,
    onRetryClick: () -> Unit,
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
            ErrorScreen(
                onRetry = onRetryClick,
                errorMessage = "Error al cargar la lista de personajes"

            )
        }
        else -> {
            CharacterListScreen(characters, onCharacterClick, modifier)
        }
    }
}

@Composable
private fun CharacterListScreen(
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(characters) { item ->
            CharacterItem(
                character = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onCharacterClick(item.id) }
            )
        }
    }
}

@Composable
private fun CharacterItem(
    character: Character,
    modifier: Modifier = Modifier
) {
    val imageBackgroundColors = listOf(
        MaterialTheme.colorScheme.error,
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.secondary,
        MaterialTheme.colorScheme.tertiary,
        MaterialTheme.colorScheme.primaryContainer,
        MaterialTheme.colorScheme.secondaryContainer,
        MaterialTheme.colorScheme.tertiaryContainer,
        MaterialTheme.colorScheme.inverseSurface
    )
    Row(
        modifier = modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(48.dp),
            color = imageBackgroundColors[(character.id % (imageBackgroundColors.count() - 1))],
            shape = CircleShape
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.image)
                        .crossfade(true)
                        .build(),
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                )
            }
        }
        Column {
            Text(text = character.name)
            Text(
                text = "${character.species} * ${character.status}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun PreviewCharacterListScreen() {
    RickAndMortyTheme {
        Surface {
            val db = CharacterDb()
            CharacterListScreen(
                characters = db.getAllCharacters().take(6),
                onCharacterClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}