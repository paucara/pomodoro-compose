package com.example.pomodorocompose.ui.screens.settings

sealed class PomodoroOptions(val increment : Int){
    data object PomodoroDuration : PomodoroOptions(5)
    data object ShortBreak : PomodoroOptions(5)
    data object LongBreak : PomodoroOptions(5)
    data object PomodoroLoops : PomodoroOptions(1)
}