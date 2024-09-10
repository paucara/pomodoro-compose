package com.example.pomodorocompose.ui.screens.pomodoro

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.example.pomodorocompose.domain.Pomodoro
import com.example.pomodorocompose.utils.formatTime
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PomodoroViewModel  @Inject constructor(
    private val pomodoro : Pomodoro
) : ViewModel(){

    val uiState = pomodoro.pomodoroPause
    val time = pomodoro.formattedTime

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


