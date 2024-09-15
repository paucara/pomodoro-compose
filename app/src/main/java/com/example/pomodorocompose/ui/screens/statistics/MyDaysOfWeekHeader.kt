package com.example.pomodorocompose.ui.screens.statistics

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import java.time.DayOfWeek
import java.time.format.TextStyle.SHORT
import java.util.Locale

@Composable
fun MyDaysOfWeekHeader(
    daysOfWeek: List<DayOfWeek>,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        daysOfWeek.forEach { dayOfWeek ->
            Text(
                textAlign = TextAlign.Center,
                text = dayOfWeek.getDisplayName(SHORT, Locale.getDefault()),
                modifier = modifier
                    .weight(1f)
                    .wrapContentHeight(),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}