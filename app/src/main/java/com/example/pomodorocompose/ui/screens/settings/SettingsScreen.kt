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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
){
    PomodoroOptions()
}

@Composable
fun PomodoroOptions() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SelectNumberButton("Focus Count", 4, 1)
        SelectNumberButton("Focus Time", 25, 5)
        SelectNumberButton("Break Time", 5, 5)
        SelectNumberButton("Long Break Time", 15, 5)
    }
}

@Composable
fun SelectNumberButton(title : String, default : Int, counter : Int) {
    Row(
        modifier = Modifier.padding(horizontal = 50.dp),
        verticalAlignment = Alignment.CenterVertically

    ) {
        Text(text = title, textAlign = TextAlign.Start, modifier = Modifier.weight(2F))
        Row(modifier = Modifier.weight(1F),verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier.weight(1F)){
                CounterButton(Icons.Filled.KeyboardArrowLeft) {}
            }
            Box(modifier = Modifier.weight(1F), contentAlignment = Alignment.Center){
                NumberValue(default)
            }
            Box(modifier = Modifier.weight(1F)){
                CounterButton(Icons.Filled.KeyboardArrowRight) {}
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
        onClick = { onClick }
    ){
        Icon(imageVector = icon, contentDescription = null)
    }
}

@Preview
@Composable
fun PomodoroOptionsPreview() {
    PomodoroOptions()
}