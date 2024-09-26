package Lab9.Profile

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rickandmorty.ui.login.LoginRoute
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavGraphBuilder.profileScreen(
    onLogoutClick: () -> Unit
) {
    composable<ProfileDestination> {
        ProfileScreen(
            onLogoffClick = onLogoutClick
        )
    }
}