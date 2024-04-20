package com.example.pomodorocompose.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pomodorocompose.model.Pomodoro

class PomodoroViewModel : ViewModel() {

    private val pomodoro = Pomodoro(viewModelScope)

    val uiState = pomodoro.pomodoroPause
    val time = pomodoro.formattedTime
    val message = pomodoro.message

    fun start(){
        pomodoro.start()
    }
    fun cancel(){
        pomodoro.cancel()
    }
    fun pause(){
        pomodoro.pause()
    }

}


