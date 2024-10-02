package com.example.pomodorocompose.data.statistics

import com.example.pomodorocompose.data.model.Statistic
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface StatisticsRepository{
    fun insertStatistic(statistic: Statistic)
    fun getAllStatistic(): Flow<List<Statistic>>
}