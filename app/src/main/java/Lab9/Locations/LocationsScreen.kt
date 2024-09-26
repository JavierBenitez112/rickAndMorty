package Lab9.Locations


import Location
import LocationDb
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.rickandmorty.ui.theme.RickAndMortyTheme


@Composable
fun LocationRoute(
    onLocationClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val locationsState by remember { mutableStateOf(LocationDb().getAllLocations()) }

    LocationScreen(onLocationClick = onLocationClick, locations = locationsState, modifier = modifier)
}

@Composable
fun cardLocation(location: Location, onLocationClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onLocationClick(location.id) }
    ) {
        Text(
            text = location.name,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            text = location.type,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

// Función para crear la lista perezosa de ubicaciones
@Composable
fun lazyLocationList(onLocationClick: (Int) -> Unit) {
    val locations = LocationDb().getAllLocations()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(locations) { location ->
            cardLocation(location = location, onLocationClick = onLocationClick)
        }
    }
}

// Función para la pantalla principal de ubicaciones
@Composable
fun LocationScreen(
    onLocationClick: (Int) -> Unit,
    locations: List<Location>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { barTop() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
        ) {
            lazyLocationList(onLocationClick = onLocationClick)
        }
    }
}

// Barra superior con título
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun barTop() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text("Locations")
        })
}

// Función para la vista previa en el IDE
@Preview
@Composable
fun PreviewLocationList() {
    RickAndMortyTheme {
        val navController = rememberNavController()
        LocationScreen(onLocationClick = {}, locations = LocationDb().getAllLocations())
    }
}