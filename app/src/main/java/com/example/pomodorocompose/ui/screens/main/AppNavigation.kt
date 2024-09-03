package com.example.pomodorocompose.ui.screens.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class Screen {
    POMODORO,
    SETTINGS,
    STATISTICS
}
sealed class NavigationItem(val route: String, val icon: ImageVector) {
    data object Pomodoro : NavigationItem(Screen.POMODORO.name, Icons.Filled.Home)
    data object Settings : NavigationItem(Screen.SETTINGS.name, Icons.Filled.Settings)
    data object Statistics : NavigationItem(Screen.STATISTICS.name, Icons.Filled.DateRange)
}