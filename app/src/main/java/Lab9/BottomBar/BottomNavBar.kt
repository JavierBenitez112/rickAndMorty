package Lab9.BottomBar

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import Lab9.LocationNavGraph
import Lab9.Profile.ProfileScreen
import Lab9.locationGraph
import androidx.compose.foundation.layout.PaddingValues
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.rickandmorty.characterNavGraph


@Composable
fun bottomBarNavigation(
    onNavItemClick: (String) -> Unit,
    checkItemSelected: @Composable (String) -> Boolean
) {
    NavigationBar {
        BottomNavigationItems.forEach { bottomNavigationItem ->
            val isItemSelected = checkItemSelected(bottomNavigationItem.destination as String)
            NavigationBarItem(
                selected = isItemSelected,
                onClick = {
                    onNavItemClick(bottomNavigationItem.destination as String)
                },
                label = { Text(text = bottomNavigationItem.title) },
                icon = {
                    Icon(
                        imageVector = if (isItemSelected) bottomNavigationItem.selectedIcon else bottomNavigationItem.unselectedIcon,
                        contentDescription = bottomNavigationItem.title
                    )
                }
            )
        }
    }
}

@Composable
fun BottomBarScreen(
    onLogoutClick: () -> Unit,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        bottomBar = {
            bottomBarNavigation(
                onNavItemClick = { destination ->
                    navController.navigate(destination) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                checkItemSelected = { destination ->
                    navController.currentBackStackEntryAsState().value?.destination?.hierarchy?.any { it.route == destination }
                        ?: false
                }
            )
        }
    ) { innerPadding: PaddingValues ->
        NavHost(
            navController = navController,
            startDestination = "characters",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable("characters") {
                characterNavGraph(
                    paddingValues = innerPadding,
                    onLoginClick = { /* Handle login click */ },
                    onCharacterClick = { characterId -> /* Handle character click */ },
                    onBackToLogin = { /* Handle back to login */ }
                )
            }
            composable("location") {
                LocationNavGraph(innerPadding)
            }
            composable("profile") {
                ProfileScreen(
                    onLogoffClick = onLogoutClick
                )
            }
        }
    }
}