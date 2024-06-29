package com.example.pomodorocompose.data.model

data class PomodoroSettings(
    var pomodoroDuration : Int,
    var longRestDuration : Int,
    var shortRestDuration : Int,
    var pomodoroLoops : Int
)