package com.example.pomodorocompose.ui.screens.statistics

import androidx.lifecycle.ViewModel
import com.example.pomodorocompose.data.statistics.StatisticsRepository
import com.example.pomodorocompose.data.model.Statistic
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.emptyFlow
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    statisticsRepository: StatisticsRepository
) : ViewModel() {

    var statistic = emptyFlow<List<Statistic>>()

    init {
        statistic = statisticsRepository.getAllStatistic()
    }
}