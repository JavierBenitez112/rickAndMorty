package com.example.rickandmorty.App.prinFlow.Character.Lista

import android.content.res.Configuration
import androidx.compose.foundation.background
import com.example.rickandmorty.Datos.localDb.sourceDb.CharacterDb
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
import com.example.rickandmorty.Datos.model.Characters
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.rickandmorty.App.comun.ErrorScreen
import com.example.rickandmorty.App.comun.LoadingScreen


@Composable
fun CharacterRoute(
    onCharacterClick: (Int) -> Unit,
    viewModel: CharacterViewModel = viewModel(factory = CharacterViewModel.Factory)
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    CharacterScreen(
        state = state,
        forceError = { viewModel.onEvent(CharactersEvent.ForceError) },
        onCharacterClick = onCharacterClick,
        onRetryClick = { viewModel.onEvent(CharactersEvent.RetryClick) },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
private fun CharacterScreen(
    state: CharactersState,
    forceError: () -> Unit,
    onRetryClick: () -> Unit,
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
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
                errorMessage = "Error al cargar la lista de personajes"

            )
        }
        else -> {
            CharacterListScreen(state.data.take(20), onCharacterClick, modifier)
        }
    }
}

@Composable
private fun CharacterListScreen(
    characters: List<Characters>,
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
    character: Characters,
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