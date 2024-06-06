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
import com.example.pomodorocompose.domain.NotificationManager
import com.example.pomodorocompose.ui.screens.main.MainScreen
import com.example.pomodorocompose.ui.screens.main.MainViewModel
import com.example.pomodorocompose.ui.theme.PomodoroComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()

    @Inject lateinit var notificationManager : NotificationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        notificationManager = NotificationManager(this)

        lifecycleScope.launch {
            viewModel.message.drop(1).collect{message->
                notificationManager.createAndShowNotification(message)
            }
        }

        setContent {
            PomodoroComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}
