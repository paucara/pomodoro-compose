package com.example.pomodorocompose.model

data class PomodoroSettings(
    val pomodoroDuration : Long,
    val longRestDuration : Long,
    val shortRestDuration : Long,
    val pomodoroLoops : Int
)