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
import com.example.pomodorocompose.model.PomodoroSettings

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
){
    val settings = viewModel.settings
    val saveButton by viewModel.saveButton
    PomodoroOptions(
        saveButton,
        settings,
        viewModel::setPomodoroLoops,
        viewModel::setPomodoroDuration,
        viewModel::setLongRestDuration,
        viewModel::setShortRestDuration,
        viewModel::saveChanges,
        viewModel::cancelChanges
    )
}
@Composable
fun PomodoroOptions(
    saveButton: Float,
    settings: PomodoroSettings?,
    setPomodoroLoops: (Int) -> Unit,
    setPomodoroDuration: (Int) -> Unit,
    setLongRestDuration: (Int) -> Unit,
    setShortRestDuration: (Int) -> Unit,
    saveChanges: () -> Unit,
    cancelChanges: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        settings?.let {
            SelectNumberButton("Focus Count", settings.pomodoroLoops, setPomodoroLoops, 1)
            SelectNumberButton("Focus Time", settings.pomodoroDuration, setPomodoroDuration, 5)
            SelectNumberButton("Break Time", settings.shortRestDuration, setShortRestDuration, 5)
            SelectNumberButton("Long Break Time", settings.longRestDuration,setLongRestDuration, 5)
        }
        Row {
            Button(onClick = { saveChanges() }, modifier = Modifier.alpha(saveButton)) {
                Text(text = "Save")
            }
            Button(onClick = { cancelChanges() }, modifier = Modifier.alpha(saveButton)) {
                Text(text = "Cancel")
            }
        }
    }
}

@Composable
fun SelectNumberButton(title: String, default: Int, onClick: (Int) -> Unit, increment: Int) {
    Row(
        modifier = Modifier.padding(horizontal = 50.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(text = title, textAlign = TextAlign.Start, modifier = Modifier.weight(2F))
        Row(modifier = Modifier.weight(1F),verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(1F)){
                CounterButton(Icons.Filled.KeyboardArrowLeft) { onClick(-increment) }
            }
            Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center){
                NumberValue(default)
            }
            Box(modifier = Modifier.weight(1F)){
                CounterButton(Icons.Filled.KeyboardArrowRight){ onClick(increment) }
            }
        }
    }
}



@Composable
fun NumberValue(value : Int) {
    Text(text = value.toString())
}

@Composable
fun CounterButton(icon : ImageVector, onClick : () -> Unit) {
    IconButton(
        onClick = { onClick() }
    ){
        Icon(imageVector = icon, contentDescription = null)
    }
}
