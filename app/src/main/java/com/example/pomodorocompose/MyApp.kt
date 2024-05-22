package com.example.pomodorocompose

import android.app.Application
import android.app.NotificationChannel
import android.util.Log
import com.example.pomodorocompose.model.NotificationManager

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.d("MyApp", "onCreate")
    }

}