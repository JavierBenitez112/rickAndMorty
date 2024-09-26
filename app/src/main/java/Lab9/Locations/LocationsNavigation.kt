package Lab9.Locations
import Lab8.Characters.CharacterDestination
import Lab8.Characters.CharacterRoute
import androidx.navigation.*
import androidx.navigation.compose.composable
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Serializable
data object LocationDestination

fun NavGraphBuilder.LocationScreen(
    onCharacterClick: (Int) -> Unit,
    onBackToLogin: () -> Unit
) {
    composable<CharacterDestination> {
        CharacterRoute(
            onCharacterClick = onCharacterClick,
            modifier = Modifier.fillMaxWidth()
        )


        BackHandler {
            onBackToLogin()
        }
    }
}