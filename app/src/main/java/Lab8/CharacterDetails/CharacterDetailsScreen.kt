package Lab8.CharacterDetails

import CharacterDb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest

import Character
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.layout.ContentScale


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailRoute(
    characterId: Int,
    navController: NavHostController
) {
    val character = CharacterDb().getCharacterById(characterId)
    CharacterDetailScreen(
        character = character,
        onBackClick = { navController.navigateUp() }

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    character: Character,
    onBackClick: () -> Unit
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
                    IconButton(onClick = { onBackClick() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues),
            contentAlignment = Alignment.TopCenter
        ) {
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
                            .data(character.image)
                            .crossfade(true)
                            .build(),
                        contentDescription = character.name,
                        modifier = Modifier
                            .size(192.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                // Nombre del personaje
                Text(
                    text = character.name,
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
                        Text(text = character.species, style = MaterialTheme.typography.bodyMedium)
                        Text(text = character.status, style = MaterialTheme.typography.bodyMedium)
                        Text(text = character.gender, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}