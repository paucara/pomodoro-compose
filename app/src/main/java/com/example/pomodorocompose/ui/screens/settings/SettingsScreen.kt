package com.example.pomodorocompose.ui.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import com.example.pomodorocompose.data.model.PomodoroSettings

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {
    LifecycleEventEffect(event = Lifecycle.Event.ON_STOP) {
        viewModel.setButtons(0F)
        viewModel.updatedSettings()
    }

    val settings = viewModel.settings
    val saveButton by viewModel.buttons

    PomodoroOptions(
        saveButton,
        settings,
        viewModel::setPomodoroSettings,
        viewModel::saveChanges,
        viewModel::cancelChanges
    )
}

@Composable
fun PomodoroOptions(
    saveButton: Float,
    settings: PomodoroSettings?,
    setPomodoroSettings: (Int, PomodoroOptions) -> Unit,
    saveChanges: () -> Unit,
    cancelChanges: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        settings?.let {
            SelectNumberButton(
                "Focus Count",
                settings.pomodoroLoops,
                setPomodoroSettings,
                PomodoroOptions.PomodoroLoops.increment,
                PomodoroOptions.PomodoroLoops
            )
            SelectNumberButton(
                "Focus Time",
                settings.pomodoroDuration,
                setPomodoroSettings,
                PomodoroOptions.PomodoroDuration.increment,
                PomodoroOptions.PomodoroDuration
            )
            SelectNumberButton(
                "Break Time",
                settings.shortRestDuration,
                setPomodoroSettings,
                PomodoroOptions.ShortBreak.increment,
                PomodoroOptions.ShortBreak
            )
            SelectNumberButton(
                "Long Break Time",
                settings.longRestDuration,
                setPomodoroSettings,
                PomodoroOptions.LongBreak.increment,
                PomodoroOptions.LongBreak
            )
        }
        Row(modifier = Modifier.alpha(saveButton)) {
            SettingsChangeButton("Save", saveChanges
            )
            SettingsChangeButton("Cancel", cancelChanges
            )
        }
    }
}

@Composable
fun SettingsChangeButton(text : String, onClick : ()->Unit){
    Button(onClick = onClick, modifier = Modifier.padding(20.dp)){
        Text(text = text)
    }
}

@Composable
fun SelectNumberButton(
    title: String,
    duration: Int,
    onClick: (Int, PomodoroOptions) -> Unit,
    increment: Int,
    option: PomodoroOptions
) {
    Row(
        modifier = Modifier.padding(horizontal = 50.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(text = title, textAlign = TextAlign.Start, modifier = Modifier.weight(2F))
        Row(modifier = Modifier.weight(1F), verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(1F)) {
                CounterButton(Icons.Filled.KeyboardArrowLeft) { onClick(-increment, option) }
            }
            Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center) {
                NumberValue(duration)
            }
            Box(modifier = Modifier.weight(1F)) {
                CounterButton(Icons.Filled.KeyboardArrowRight) { onClick(increment, option) }
            }
        }
    }
}


@Composable
fun NumberValue(value: Int) {
    Text(text = value.toString())
}

@Composable
fun CounterButton(icon: ImageVector, onClick: () -> Unit) {
    IconButton(
        onClick = { onClick() }
    ) {
        Icon(imageVector = icon, contentDescription = null)
    }
}
