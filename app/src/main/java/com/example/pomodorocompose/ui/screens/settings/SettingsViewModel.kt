package com.example.pomodorocompose.ui.screens.settings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorocompose.domain.Pomodoro
import com.example.pomodorocompose.model.SettingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val pomodoro : Pomodoro
) : ViewModel() {
    val settings = settingsRepository.settings

    private val _saveButton = mutableFloatStateOf(0f)
    val saveButton : State<Float> = _saveButton

    fun setPomodoroDuration(value : Int){
        _saveButton.floatValue = 1f
        viewModelScope.launch {
            settingsRepository.setPomodoroDuration(value)
        }
    }
    fun setShortRestDuration(value: Int){
        _saveButton.floatValue = 1f
        viewModelScope.launch {
            settingsRepository.setShortRestDuration(value)
        }
    }
    fun setLongRestDuration(value: Int){
        _saveButton.floatValue = 1f
        viewModelScope.launch {
            settingsRepository.setLongRestDuration(value)
        }
    }
    fun setPomodoroLoops(value: Int){
        _saveButton.floatValue = 1f
        viewModelScope.launch {
            settingsRepository.setPomodoroLoops(value)
        }
    }
    fun saveChanges(){
        pomodoro.settingsUpdate()
        if(pomodoro.isRunning){
            pomodoro.cancel()
        }
        _saveButton.floatValue = 0f
    }
}