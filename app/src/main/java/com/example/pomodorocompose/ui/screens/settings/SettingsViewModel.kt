package com.example.pomodorocompose.ui.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorocompose.domain.Pomodoro
import com.example.pomodorocompose.data.model.PomodoroSettings
import com.example.pomodorocompose.data.settings.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val pomodoro: Pomodoro
) : ViewModel() {
    init {
        updatedSettings()
    }

    private var defaultSettings = PomodoroSettings(0, 0, 0, 0)

    var settings by mutableStateOf(PomodoroSettings(0, 0, 0, 0))

    private val _buttons = mutableFloatStateOf(0f)
    val buttons: State<Float> = _buttons

    fun setPomodoroSettings(value: Int, option: PomodoroOptions) {
        _buttons.floatValue = 1f
        settings = when (option) {

            PomodoroOptions.PomodoroDuration -> settings.copy(
                pomodoroDuration =
                if (settings.pomodoroDuration + value > 0) {
                    settings.pomodoroDuration + value
                } else {
                    settings.pomodoroDuration
                }
            )

            PomodoroOptions.ShortBreak -> settings.copy(
                shortRestDuration =
                if (settings.shortRestDuration + value > 0) {
                    settings.shortRestDuration + value
                } else {
                    settings.shortRestDuration
                }
            )

            PomodoroOptions.LongBreak -> settings.copy(
                longRestDuration =
                if (settings.longRestDuration + value > 0) {
                    settings.longRestDuration + value
                } else {
                    settings.longRestDuration
                }
            )

            PomodoroOptions.PomodoroLoops -> settings.copy(
                pomodoroLoops =
                if (settings.pomodoroLoops + value > 0) {
                    settings.pomodoroLoops + value
                } else {
                    settings.pomodoroLoops
                }
            )
        }
        if (settings == defaultSettings) {
            _buttons.floatValue = 0f
        }
    }

    fun saveChanges() {
        viewModelScope.launch {
            settingsRepository.setPomodoroLoops(settings.pomodoroLoops)
            settingsRepository.setPomodoroDuration(settings.pomodoroDuration)
            settingsRepository.setShortRestDuration(settings.shortRestDuration)
            settingsRepository.setLongRestDuration(settings.longRestDuration)
            pomodoro.settingsUpdate()
            updatedSettings()
        }
        if (pomodoro.isRunning) {
            pomodoro.cancel()
        }
        _buttons.floatValue = 0f
    }

    fun cancelChanges() {
        _buttons.floatValue = 0f
        updatedSettings()
    }

    fun setButtons(value: Float) {
        _buttons.floatValue = value
    }

    fun updatedSettings() {
        viewModelScope.launch {
            settings = settingsRepository.getSettings()
            defaultSettings = settings
        }
    }
}