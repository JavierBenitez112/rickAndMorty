package Lab8.Characters

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rickandmorty.ui.login.LoginRoute
import kotlinx.serialization.Serializable


@Serializable
data object LoginDestination

fun NavGraphBuilder.loginScreen(
    onLoginClick: () -> Unit
){
    composable<LoginDestination>{
        LoginRoute(
            onLoginClick = onLoginClick,

        )
    }
}
