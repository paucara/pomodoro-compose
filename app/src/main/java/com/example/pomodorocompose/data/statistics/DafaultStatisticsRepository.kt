package com.example.pomodorocompose.data.statistics

import com.example.pomodorocompose.data.model.Statistic
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultStatisticsRepository @Inject constructor(
    private val statisticsDao: StatisticsDao
) : StatisticsRepository{
    override fun insertStatistic(statistic: Statistic) {
        statisticsDao.insert(statistic)
    }

    override fun getAllStatistic(): Flow<List<Statistic>> {
        return statisticsDao.getAll()
    }
}