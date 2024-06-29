package com.example.pomodorocompose.domain

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.pomodorocompose.R

class NotificationManager(
    private val context: Context
){
    private val notificationManager: NotificationManagerCompat =
        NotificationManagerCompat.from(context).also {
        it.createNotificationChannel(createNotificationChannel())
    }

    private fun createNotificationChannel() : NotificationChannel{
        val name = CHANNEL_NAME
        val descriptionText = DESCRIPTION
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        return NotificationChannel(MY_CHANNEL_ID, name, importance).apply {
            description = descriptionText
        }
    }

    private fun createNotification(message: String?) : Notification{
        val builder = NotificationCompat.Builder(context, MY_CHANNEL_ID)
            .setSmallIcon(R.drawable.alarm)
            .setContentTitle("Pomodoro")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        return builder.build()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun showNotification(notification : Notification) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ){
            requestNotificationPermission()
        }
        notificationManager.notify(MY_NOTIFICATION_ID, notification)
    }

    fun createAndShowNotification(message: String) {
        showNotification(createNotification(message))
    }

    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
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
                    456
                )
            }
        }
    }

    companion object {
        private const val CHANNEL_NAME = "Channel Name"
        private const val DESCRIPTION = "Notification Channel Description"
        private const val MY_CHANNEL_ID = "My_Channel_ID"
        private const val MY_NOTIFICATION_ID = 123
    }
}