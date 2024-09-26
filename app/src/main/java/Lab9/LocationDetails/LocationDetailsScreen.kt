package Lab9.LocationDetails

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
import Location
import LocationDb
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale

@Composable
fun LocationDetailRoute(
    locationId: Int,
    navController: NavHostController
) {
    val location = remember { LocationDb().getLocationById(locationId) }
    LocationDetailScreen(location = location, onBackClick = { navController.popBackStack() })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocationDetailScreen(
    location: Location,
    onBackClick: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Location Details") }, // Título de la barra superior
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
                // Nombre de la ubicación
                Text(
                    text = location.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    // Columnas de etiquetas de los detalles
                    Column {
                        Text(text = "ID:", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Type:", style = MaterialTheme.typography.bodyMedium)
                        Text(text = "Dimensions:", style = MaterialTheme.typography.bodyMedium)
                    }
                    // Columnas de valores de los detalles
                    Column {
                        Text(text = location.id.toString(), style = MaterialTheme.typography.bodyMedium)
                        Text(text = location.type, style = MaterialTheme.typography.bodyMedium)
                        Text(text = location.dimension, style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}
