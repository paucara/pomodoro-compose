package com.example.pomodorocompose.model

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

class Pomodoro(private val scope : CoroutineScope) {

    private val pomodoroFlow = MutableStateFlow(POMODORO_DURATION)
    private var pomodoroJob: Job? = null
    private var pomodoroBreak = true
    private var pomodoroLoops = 0

    private val _pomodoroPause = MutableStateFlow(false)
    val pomodoroPause: StateFlow<Boolean> = _pomodoroPause.asStateFlow()

    private var _message = MutableStateFlow("")
    val message : StateFlow<String> = _message.asStateFlow()

    val formattedTime: Flow<String> = pomodoroFlow.map { formatTime(it) }

    fun start() {
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
        pomodoroFlow.value = POMODORO_DURATION
        pomodoroBreak = true
        pomodoroLoops = 0
    }

    private fun pomodoro() {
        _pomodoroPause.value = false
        pomodoroJob?.cancel()
        if (pomodoroBreak) {
            if (pomodoroLoops < POMODORO_LOOPS) {
                pomodoroFlow.value = SHORT_REST_DURATION
                pomodoroLoops++
            } else {
                pomodoroFlow.value = LONG_REST_DURATION
                pomodoroLoops = 0
            }

            _message.value = "Time to rest"
            pomodoroBreak = false
        } else {
            pomodoroFlow.value = POMODORO_DURATION
            pomodoroBreak = true
            _message.value = "Time to study"

        }
    }

    companion object {
        private const val POMODORO_DURATION = 5000L
        private const val LONG_REST_DURATION = 3000L
        private const val SHORT_REST_DURATION = 2000L
        private const val SECOND_DURATION = 1000L
        private const val POMODORO_LOOPS = 3
    }

}