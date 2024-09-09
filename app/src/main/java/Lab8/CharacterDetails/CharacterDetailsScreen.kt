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
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import Character
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.layout.ContentScale

// Example CharacterDb instance for demo purposes
val characterDb = CharacterDb()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailRoute(
    characterId: Int,
    onNavigateBack: () -> Unit
) {
    CharacterDetailScreen(
        characterId = characterId,
        onBackClick = onNavigateBack
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailScreen(
    characterId: Int,
    onBackClick: () -> Unit
) {
    // Obtener el personaje usando el ID
    val character = characterDb.getCharacterById(characterId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Detail") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { onBackClick }) {
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
                // Imagen del personaje
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
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.clip(CircleShape)
                    )
                }

                // Nombre del personaje
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )

                // Detalles adicionales (Species, Status, Gender)
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

// Preview para visualizar la pantalla
@Preview
@Composable
fun PreviewCharacterDetail() {
    RickAndMortyTheme {
        val navController = rememberNavController()
        CharacterDetailScreen(characterId = 1, onBackClick = { navController.popBackStack() })
    }
}
