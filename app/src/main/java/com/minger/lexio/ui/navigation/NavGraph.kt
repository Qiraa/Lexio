package com.minger.lexio.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.minger.lexio.ui.main.MainScreen
import com.minger.lexio.ui.decks.DecksScreen
import com.minger.lexio.ui.profile.ProfileScreen

@Composable
fun NavGraph(navController: NavController = rememberNavController()) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute in bottomBarScreens) {
                NavigationBar {
                    NavigationBarItem(
                        selected = currentRoute == Screen.Main.route,
                        onClick = {
                            navController.navigate(Screen.Main.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Home, contentDescription = null) },
                        label = { Text("Main") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.Decks.route,
                        onClick = {
                            navController.navigate(Screen.Decks.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Book, contentDescription = null) },
                        label = { Text("Decks") }
                    )
                    NavigationBarItem(
                        selected = currentRoute == Screen.Profile.route,
                        onClick = {
                            navController.navigate(Screen.Profile.route) {
                                popUpTo(navController.graph.startDestinationId) { saveState = true}
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(Icons.Default.Person, contentDescription = null) },
                        label = { Text("Profile") }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController as NavHostController,
            startDestination = Screen.Main.route,
            modifier = Modifier.padding(paddingValues)
        ) {
           composable(Screen.Main.route) { MainScreen(userName = "Даниил", onAllDecksClick = {}) }
           composable(Screen.Decks.route) { DecksScreen() }
           composable(Screen.Profile.route) { ProfileScreen() }
        }
    }
}
