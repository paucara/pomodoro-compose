package com.example.pomodorocompose.ui.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorocompose.domain.Pomodoro
import com.example.pomodorocompose.model.PomodoroSettings
import com.example.pomodorocompose.model.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val pomodoro : Pomodoro
) : ViewModel() {
    init{
        updatedSettings()
    }

    var settings by mutableStateOf(PomodoroSettings(0,0,0,0))
    private val _saveButton = mutableFloatStateOf(0f)
    val saveButton : State<Float> = _saveButton

    fun setPomodoroDuration(value : Int){
        _saveButton.floatValue = 1f
        settings = settings.copy(pomodoroDuration = settings.pomodoroDuration + value)
    }
    fun setShortRestDuration(value: Int){
        _saveButton.floatValue = 1f
        settings = settings.copy(shortRestDuration = settings.shortRestDuration + value)
    }
    fun setLongRestDuration(value: Int){
        _saveButton.floatValue = 1f
        settings = settings.copy(longRestDuration = settings.longRestDuration + value)
    }
    fun setPomodoroLoops(value: Int){
        _saveButton.floatValue = 1f
        settings = settings.copy(pomodoroLoops = settings.pomodoroLoops + value)
    }
    fun saveChanges(){

        viewModelScope.launch {
            settingsRepository.setPomodoroLoops(settings.pomodoroLoops)
            settingsRepository.setPomodoroDuration(settings.pomodoroDuration)
            settingsRepository.setShortRestDuration(settings.shortRestDuration)
            settingsRepository.setLongRestDuration(settings.longRestDuration)
            pomodoro.settingsUpdate()
            updatedSettings()
        }

        if(pomodoro.isRunning){
            pomodoro.cancel()
        }

        _saveButton.floatValue = 0f
    }

    fun cancelChanges(){
        updatedSettings()
        _saveButton.floatValue = 0f
    }

    private fun updatedSettings(){
        viewModelScope.launch {
            settings = settingsRepository.getSettings()
        }
    }
}