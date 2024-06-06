package com.example.pomodorocompose

import androidx.lifecycle.ViewModel
import com.example.pomodorocompose.domain.Pomodoro
import javax.inject.Inject

class TestViewModel @Inject constructor(
    private val pomodoro: Pomodoro
) : ViewModel(){

}