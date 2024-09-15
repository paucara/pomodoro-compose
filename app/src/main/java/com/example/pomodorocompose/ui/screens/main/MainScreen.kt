package com.example.pomodorocompose.ui.screens.main

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pomodorocompose.ui.screens.pomodoro.PomodoroScreen
import com.example.pomodorocompose.ui.screens.settings.SettingsScreen
import com.example.pomodorocompose.ui.screens.statistics.StatisticsScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) {
        MyNavHost(navController = navController, it)
    }
}

@Composable
fun MyNavHost(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(
        modifier = Modifier.padding(paddingValues),
        navController = navController,
        startDestination = NavigationItem.Pomodoro.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None }

    ) {
        composable(
            NavigationItem.Pomodoro.route
        ) { PomodoroScreen() }
        composable(
            NavigationItem.Settings.route
        ) { SettingsScreen() }
        composable(
            NavigationItem.Statistics.route
        ) { StatisticsScreen() }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        NavigationItem.Pomodoro,
        NavigationItem.Settings,
        NavigationItem.Statistics
    )
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background,
        tonalElevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary,
                    selectedIconColor = MaterialTheme.colorScheme.onPrimary
                ),
                icon = { Icon(item.icon, contentDescription = null) },
                label = { Text(item.route) },
                onClick = {
                    navController.navigate(item.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                selected = currentRoute == item.route
            )
        }
    }
}
