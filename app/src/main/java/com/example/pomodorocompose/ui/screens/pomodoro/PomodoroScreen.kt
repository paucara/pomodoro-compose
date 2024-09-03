package com.example.pomodorocompose.ui.screens.pomodoro

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dotlottie.dlplayer.Mode
import com.example.pomodorocompose.R
import com.lottiefiles.dotlottie.core.compose.ui.DotLottieAnimation
import com.lottiefiles.dotlottie.core.util.DotLottieSource


@Composable
fun PomodoroScreen(
    viewModel: PomodoroViewModel = hiltViewModel()
){
    val time = viewModel.time.collectAsStateWithLifecycle(initialValue = null)
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.weight(1.5F)
        ) {
            Timer(time.value, modifier = Modifier.padding(bottom = 10.dp))
            Buttons(viewModel::start, viewModel::pause, viewModel::cancel, uiState.value, modifier = Modifier)
        }
        val visible = if (uiState.value) 1F else 0F
        LottieAnimation(modifier = Modifier.weight(1F).alpha(visible))
    }
}

@Composable
fun LottieAnimation(modifier : Modifier) {
    DotLottieAnimation(
        source = DotLottieSource.Asset("animation.json"),
        autoplay = true,
        loop = true,
        speed = 3.0f,
        useFrameInterpolation = false,
        playMode = Mode.FORWARD,
        modifier = modifier.background(Color.Transparent)
    )
}

@Composable
fun Buttons(
    start : ()->Unit,
    pause : ()->Unit,
    stop : ()->Unit,
    uiState: Boolean,
    modifier : Modifier
) {
    Row(
        modifier = modifier.animateContentSize()
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
fun Timer(time: String?, modifier : Modifier) {
    if (time != null) {
        Text(
            text = time,
            style = MaterialTheme.typography.displayLarge,
            modifier = modifier
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