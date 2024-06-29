package com.example.pomodorocompose.data

import com.example.pomodorocompose.data.model.PomodoroSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class SettingsRepository @Inject constructor(private val settingsDataSource: SettingsDataSource) {

    val settings: Flow<PomodoroSettings> =
        combine(
            settingsDataSource.pomodoroDuration,
            settingsDataSource.longRestDuration,
            settingsDataSource.shortRestDuration,
            settingsDataSource.pomodoroLoops,
        ) { pomodoroDuration, longRestDuration, shortRestDuration, pomodoroLoops ->
            PomodoroSettings(pomodoroDuration, longRestDuration, shortRestDuration, pomodoroLoops)
        }

    suspend fun setPomodoroDuration(value : Int){
        settingsDataSource.setPomodoroDuration(value)
    }
    suspend fun setLongRestDuration(value : Int){
        settingsDataSource.setLongRestDuration(value)
    }
    suspend fun setShortRestDuration(value : Int){
        settingsDataSource.setShortRestDuration(value)
    }
    suspend fun setPomodoroLoops(value : Int){
        settingsDataSource.setPomodoroLoops(value)
    }

    suspend fun getSettings(): PomodoroSettings {
        return settingsDataSource.getSettings()
    }
}