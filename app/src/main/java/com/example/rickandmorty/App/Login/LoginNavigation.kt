package Lab8.Characters

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.rickandmorty.App.Login.LoginViewModel
import com.example.rickandmorty.ui.login.LoginRoute
import kotlinx.serialization.Serializable
import kotlin.reflect.KFunction1


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
