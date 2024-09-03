package com.example.pomodorocompose.data

import com.example.pomodorocompose.data.model.Statistic
import java.time.LocalDate
import javax.inject.Inject

class StatisticsRepository @Inject constructor(
    private val statisticsDao: StatisticsDao
) {
    private fun insertStatistic(statistic: Statistic) {
        statisticsDao.insert(statistic)
    }

    private fun getStatisticByDate(date: LocalDate): Statistic {
        return statisticsDao.getByDate(date)
    }

    private fun updateStatistic(statistic: Statistic) {
        statisticsDao.update(statistic)
    }

    fun deleteStatistic(statistic: Statistic) {
        statisticsDao.delete(statistic)
    }

    fun getAllStatistic(): List<Statistic> {
        return statisticsDao.getAll()
    }

    fun insertOrUpdateStatistic(statistic: Statistic){
        val existingStatistic = getStatisticByDate(LocalDate.now())
        if(existingStatistic == null){
            insertStatistic(statistic)
        }else{
            val statisticAux = existingStatistic.copy(pomodoroCount = existingStatistic.pomodoroCount!! + 1)
            updateStatistic(statisticAux)
        }
    }
}