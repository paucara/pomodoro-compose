package com.example.pomodorocompose.model

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pomodorocompose.R

class NotificationHelper(private val context: Context) {

    private val notificationManager: NotificationManagerCompat =
        NotificationManagerCompat.from(context)

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        val name = CHANNEL_NAME
        val descriptionText = DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(MY_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    fun showNotification(message: String) {
        val builder = NotificationCompat.Builder(context, MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.alarm)
            .setContentTitle("Pomodoro")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            requestNotificationPermission()
            return
        }

        notificationManager.notify(MY_NOTIFICATION_ID, builder.build())
    }

    private fun requestNotificationPermission() {
        val shouldShowRationale = ActivityCompat.shouldShowRequestPermissionRationale(
            context as Activity,
            Manifest.permission.POST_NOTIFICATIONS
        )

        if (shouldShowRationale) {
            Toast.makeText(
                context,
                "The application needs permission to display notifications",
                Toast.LENGTH_LONG
            ).show()
        } else {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                NOTIFICATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    companion object {
        private const val CHANNEL_NAME = "Channel Name"
        private const val DESCRIPTION = "Notification Channel Description"
        private const val MY_CHANNEL_ID = "My_Channel_ID"
        private const val MY_NOTIFICATION_ID = 123
        private const val NOTIFICATION_PERMISSION_REQUEST_CODE = 456
    }
}