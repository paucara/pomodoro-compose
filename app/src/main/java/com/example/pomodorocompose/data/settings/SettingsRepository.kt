package com.example.pomodorocompose.data.settings

import com.example.pomodorocompose.data.model.PomodoroSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

interface SettingsRepository{
    suspend fun setPomodoroDuration(value : Int)
    suspend fun setLongRestDuration(value : Int)
    suspend fun setShortRestDuration(value : Int)
    suspend fun setPomodoroLoops(value : Int)
    suspend fun getSettings(): PomodoroSettings
}