package com.example.pomodorocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.pomodorocompose.model.NotificationHelper
import com.example.pomodorocompose.ui.screens.PomodoroScreen
import com.example.pomodorocompose.ui.screens.PomodoroViewModel
import com.example.pomodorocompose.ui.theme.PomodoroComposeTheme
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private val viewModel : PomodoroViewModel by viewModels()

    private lateinit var notificationHelper : NotificationHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationHelper = NotificationHelper(this)

        lifecycleScope.launch {
            viewModel.message.drop(1).collect{message->
                notificationHelper.showNotification(message)
            }
        }

        setContent {
            PomodoroComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PomodoroScreen(viewModel)
                }
            }
        }
    }
}
