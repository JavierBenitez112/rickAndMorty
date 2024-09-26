package Lab9.BottomBar

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
data object BottomBarScreenDestination

fun NavController.navigateToBottomBarGraph(
    navOptions: NavOptions? = null
){
    this.navigate(BottomBarScreenDestination, navOptions)
}

fun NavGraphBuilder.navigateToBottomBarScreen(
    onLogoutClick: () -> Unit
){
    composable<BottomBarScreenDestination> {
        val nestedController = rememberNavController()
        BottomBarScreen(
            navController = nestedController,
            onLogoutClick = onLogoutClick
        )
    }
}