package com.example.pomodorocompose.data.settings

import com.example.pomodorocompose.data.model.PomodoroSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class DefaultSettingsRepository @Inject constructor(private val settingsDataSource: SettingsDataSource) : SettingsRepository{

    val settings: Flow<PomodoroSettings> =
        combine(
            settingsDataSource.pomodoroDuration,
            settingsDataSource.longRestDuration,
            settingsDataSource.shortRestDuration,
            settingsDataSource.pomodoroLoops,
        ) { pomodoroDuration, longRestDuration, shortRestDuration, pomodoroLoops ->
            PomodoroSettings(pomodoroDuration, longRestDuration, shortRestDuration, pomodoroLoops)
        }

    override suspend fun setPomodoroDuration(value : Int){
        settingsDataSource.setPomodoroDuration(value)
    }
    override suspend fun setLongRestDuration(value : Int){
        settingsDataSource.setLongRestDuration(value)
    }
    override suspend fun setShortRestDuration(value : Int){
        settingsDataSource.setShortRestDuration(value)
    }
    override suspend fun setPomodoroLoops(value : Int){
        settingsDataSource.setPomodoroLoops(value)
    }
    override suspend fun getSettings(): PomodoroSettings {
        return settingsDataSource.getSettings()
    }
}