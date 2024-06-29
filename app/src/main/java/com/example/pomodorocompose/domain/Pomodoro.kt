package com.example.pomodorocompose.domain

import com.example.pomodorocompose.data.SettingsRepository
import com.example.pomodorocompose.utils.formatTime
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class Pomodoro @Inject constructor(
    private val scope : CoroutineScope,
    private val notificationManager: NotificationManager,
    private val settingsRepository: SettingsRepository
){
    init {
        settingsUpdate()
    }

    private var pomodoroDuration = 0L
    private var longRestDuration = 0L
    private var shortRestDuration = 0L
    private var pomodoroLoops = 0

    private var pomodoroFlow = MutableStateFlow(pomodoroDuration)
    private var pomodoroJob: Job? = null
    private var pomodoroBreak = true
    private var currentLoops = 0

    private val _pomodoroPause = MutableStateFlow(false)
    val pomodoroPause: StateFlow<Boolean> = _pomodoroPause.asStateFlow()

    val formattedTime: Flow<String> = pomodoroFlow.map { formatTime(it) }

    var isRunning = false

    fun start() {
        isRunning = true
        _pomodoroPause.value = true
        pomodoroJob = scope.launch {
            while (pomodoroFlow.value > 0) {
                pomodoroFlow.value -= SECOND_DURATION
                delay(SECOND_DURATION)
            }
            pomodoro()
        }
    }
    fun pause() {
        _pomodoroPause.value = false
        pomodoroJob?.cancel()
    }
    fun cancel() {
        _pomodoroPause.value = false
        pomodoroJob?.cancel()
        pomodoroFlow.value = pomodoroDuration
        pomodoroBreak = true
        currentLoops = 0
    }
    private fun pomodoro() {
        _pomodoroPause.value = false
        pomodoroJob?.cancel()
        if (pomodoroBreak) {
            if (currentLoops < pomodoroLoops) {
                pomodoroFlow.value = shortRestDuration
                currentLoops++
            } else {
                pomodoroFlow.value = longRestDuration
                currentLoops = 0
            }
            notificationManager.createAndShowNotification("Time to rest")
            pomodoroBreak = false
        } else {
            pomodoroFlow.value = pomodoroDuration
            pomodoroBreak = true
            notificationManager.createAndShowNotification("Time to study")
        }
    }

    fun settingsUpdate() {
        scope.launch {
            val settings = settingsRepository.getSettings()
            pomodoroDuration = (settings.pomodoroDuration * 1000).toLong()
            pomodoroFlow.value = pomodoroDuration
            longRestDuration = (settings.longRestDuration * 100).toLong()
            shortRestDuration = (settings.shortRestDuration * 1000).toLong()
            pomodoroLoops = settings.pomodoroLoops
        }
    }
    companion object{
        private const val SECOND_DURATION = 1000L
    }
}