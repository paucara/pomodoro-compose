package com.example.pomodorocompose.ui.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.pomodorocompose.R

@Composable
fun MainScreen(
    viewModel: PomodoroViewModel
){
    val time = viewModel.time.collectAsStateWithLifecycle(initialValue = null)
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Timer(time.value)
        Buttons(viewModel::start, viewModel::pause, viewModel::cancel, uiState.value)
    }
}

@Composable
fun Buttons(
    start : ()->Unit,
    pause : ()->Unit,
    stop : ()->Unit,
    uiState: Boolean
) {
    Row(
        Modifier
            .animateContentSize()
    ) {
        when(uiState){
            true -> {
                TimerButton(action = pause, text = "pause", painter = R.drawable.pause)
                Spacer(modifier = Modifier.width(5.dp))
                TimerButton(action = stop, text = "stop",  painter = R.drawable.stop)
            }
            false -> {
                TimerButton(action = start, text = "start",  painter = R.drawable.play)
            }
        }
    }
}

@Composable
fun Timer(time : String?) {
    if (time != null) {
        Text(
            text = time,
            style = MaterialTheme.typography.displayLarge
        )
    }
}

@Composable
fun TimerButton(action: () -> Unit, text: String, painter: Int) {
    Button(
        onClick = action,
        modifier = Modifier.size(60.dp),
        shape = CircleShape,
        contentPadding = PaddingValues(0.dp)
    ) {
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = painter),
            contentDescription = text
        )
    }
}