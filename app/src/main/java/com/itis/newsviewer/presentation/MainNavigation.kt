package com.itis.newsviewer.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
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
import com.itis.newsviewer.presentation.screen.detail.DetailScreen
import com.itis.newsviewer.presentation.screen.list.ListScreen
import com.itis.newsviewer.presentation.screen.settings.SettingsScreen
import com.itis.newsviewer.presentation.screen.stub.StubScreen
import com.itis.newsviewer.presentation.ui.custom.NewsViewerTheme

sealed class Screen(
    val route: String,
    @StringRes
    val name: Int,
    val icon: ImageVector,
) {
    object Detail : Screen(
        route = "detail",
        name = R.string.screen_detail,
        icon = Icons.Filled.Info
    )

    object List : Screen(
        route = "list",
        name = R.string.screen_list,
        icon = Icons.Filled.Home
    )

    object Settings : Screen(
        route = "settings",
        name = R.string.screen_settings,
        icon = Icons.Filled.Settings,
    )

    object Stub : Screen(
        route = "stub",
        name = R.string.screen_stub,
        icon = Icons.Filled.Build
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsNavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: Screen = Screen.List,
) {
    val items = listOf(
        Screen.List,
        Screen.Stub,
        Screen.Settings
    )
    Scaffold(
        bottomBar = {
            BottomNavigation(
                backgroundColor = NewsViewerTheme.colors.tintColor
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
            composable(Screen.Detail.route + "/{id}") { navBackStack ->
                val id = navBackStack.arguments?.getString("id")
                DetailScreen(
                    navController = navController,
                    id = id
                )
            }
            composable(Screen.Stub.route) { StubScreen() }
            composable(Screen.Settings.route) { SettingsScreen() }
        }
    }
}
