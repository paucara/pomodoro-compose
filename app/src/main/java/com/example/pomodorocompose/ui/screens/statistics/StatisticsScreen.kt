package com.example.pomodorocompose.ui.screens.statistics

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel()
){
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Total Pomdoro")
        Text(text = "Pomodoro Count")
    }
}