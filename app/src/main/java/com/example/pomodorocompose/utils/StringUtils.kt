package com.example.pomodorocompose.utils

import java.util.concurrent.TimeUnit

fun formatTime(millis: Long): String {
    val minutes = TimeUnit.MILLISECONDS.toMinutes(millis)
    val seconds = (millis % TimeUnit.MINUTES.toMillis(1)) / 1000
    return "%02d:%02d".format(minutes, seconds)
}