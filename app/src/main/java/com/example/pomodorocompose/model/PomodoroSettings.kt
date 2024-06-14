package com.example.pomodorocompose.model

data class PomodoroSettings(
    val pomodoroDuration : Int,
    val longRestDuration : Int,
    val shortRestDuration : Int,
    val pomodoroLoops : Int
)