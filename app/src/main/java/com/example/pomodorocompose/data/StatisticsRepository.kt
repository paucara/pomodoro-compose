package com.example.pomodorocompose.data

import com.example.pomodorocompose.data.model.Statistic
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate
import javax.inject.Inject

class StatisticsRepository @Inject constructor(
    private val statisticsDao: StatisticsDao
) {
    fun insertStatistic(statistic: Statistic) {
        statisticsDao.insert(statistic)
    }

    fun updateStatistic(statistic: Statistic) {
        statisticsDao.update(statistic)
    }

    fun deleteStatistic(statistic: Statistic) {
        statisticsDao.delete(statistic)
    }

    fun getAllStatistic(): Flow<List<Statistic>> {
        return statisticsDao.getAll()
    }
}