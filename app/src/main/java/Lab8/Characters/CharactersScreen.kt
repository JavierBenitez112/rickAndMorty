package Lab8.Characters

import CharacterDb
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.rickandmorty.ui.theme.RickAndMortyTheme
import androidx.compose.foundation.clickable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import Character
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun CharacterRoute(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val charactersState by remember { mutableStateOf(CharacterDb().getAllCharacters()) }

    CharacterScreen(onCharacterClick = onCharacterClick, characters = charactersState, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun barTop() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Characters")
        })
}

// Composable to display an individual character item
@Composable
fun cardCharacter(character: Character, onCharacterClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onCharacterClick(character.id) }  // Handle click to navigate to details
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.image)
                .crossfade(true)
                .build(),
            contentDescription = character.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)  // Make image circular
                .background(MaterialTheme.colorScheme.secondaryContainer)  // Background color using MaterialTheme
        )
        Column(
            modifier = Modifier.padding(start = 16.dp)
        ) {
            Text(text = character.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "Especie: ${character.species}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Estatus: ${character.status}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Género: ${character.gender}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

// Composable to display the list of characters in a lazy column
@Composable
fun lazyCharacterList(navController: NavHostController) {
    val characters = CharacterDb().getAllCharacters()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(characters) { character ->
            cardCharacter(character = character) { characterId ->
                navController.navigate("characterDetail/$characterId") // Navigate to detail screen with character ID
            }
        }
    }
}

// Main Composable for displaying the character list
@Composable
fun CharacterScreen(
    onCharacterClick: (Int) -> Unit,
    characters: List<Character>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { barTop() }
    ) { paddingValues ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)) {
            lazyCharacterList(navController = rememberNavController())
        }
    }
}

@Preview
@Composable
fun PreviewTodo(){
    RickAndMortyTheme {
        val navController = rememberNavController()
        CharacterScreen(onCharacterClick = {}, characters = listOf())
    }
}