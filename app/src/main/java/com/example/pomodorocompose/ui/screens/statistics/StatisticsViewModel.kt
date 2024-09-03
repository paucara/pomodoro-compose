package com.example.pomodorocompose.ui.screens.statistics

import androidx.lifecycle.ViewModel
import com.example.pomodorocompose.data.StatisticsRepository
import com.example.pomodorocompose.data.model.Statistic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val statisticsRepository: StatisticsRepository
) : ViewModel(){
    fun getAll() : List<Statistic> {
        return statisticsRepository.getAllStatistic()
    }
}