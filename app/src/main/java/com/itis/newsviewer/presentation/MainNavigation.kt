package com.itis.newsviewer.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.itis.newsviewer.R
import com.itis.newsviewer.presentation.screen.list.ListScreen

sealed class Screen(
    val route: String,
    @StringRes
    val name: Int,
    val icon: ImageVector,
) {
    object List : Screen(
        route = "list",
        name = R.string.screen_list,
        icon = Icons.Filled.Home
    )

//    object Cart : Screen(
//        route = "cart",
//        name = R.string.screen_cart,
//        icon = Icons.Filled.ShoppingCart
//    )

    object Settings : Screen(
        route = "settings",
        name = R.string.screen_settings,
        icon = Icons.Filled.Settings,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.List,
) {
    val items = listOf(
        Screen.List,
        Screen.Settings
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.LightGray
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                items.forEach { screen ->
                    BottomNavigationItem(
                        label = { Text(stringResource(id = screen.name)) },
                        icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        },
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.List.route) { ListScreen(navController) }
//            composable(Screen.Settings.route) { SettingsScreen(navController) }
        }
    }
}
