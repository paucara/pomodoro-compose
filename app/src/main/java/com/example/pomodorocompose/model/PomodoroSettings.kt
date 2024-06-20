package com.example.pomodorocompose.model

data class PomodoroSettings(
    var pomodoroDuration : Int,
    var longRestDuration : Int,
    var shortRestDuration : Int,
    var pomodoroLoops : Int
)