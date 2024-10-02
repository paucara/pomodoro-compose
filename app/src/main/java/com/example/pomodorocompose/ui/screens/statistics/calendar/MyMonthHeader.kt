package com.example.pomodorocompose.ui.screens.statistics.calendar


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.header.MonthState
import java.time.format.TextStyle.FULL
import java.util.Locale

@Composable
fun MyMonthHeader(
    monthState: MonthState,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        DecrementButton(monthState = monthState)
        Text(
            modifier = Modifier.testTag("MonthLabel"),
            text = monthState.currentMonth.month
                .getDisplayName(FULL, Locale.getDefault())
                .lowercase()
                .replaceFirstChar { it.titlecase() },
            style = MaterialTheme.typography.bodyLarge,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = monthState.currentMonth.year.toString(),
            style = MaterialTheme.typography.bodyLarge
        )
        IncrementButton(monthState = monthState)
    }
}

@Composable
private fun DecrementButton(
    monthState: MonthState,
) {
    IconButton(
        modifier = Modifier.testTag("Decrement"),
        enabled = monthState.currentMonth > monthState.minMonth,
        onClick = { monthState.currentMonth = monthState.currentMonth.minusMonths(1) }
    ) {
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            contentDescription = "Previous",
        )
    }
}

@Composable
private fun IncrementButton(
    monthState: MonthState,
) {
    IconButton(
        modifier = Modifier.testTag("Increment"),
        enabled = monthState.currentMonth < monthState.maxMonth,
        onClick = { monthState.currentMonth = monthState.currentMonth.plusMonths(1) }
    ) {
        Image(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            contentDescription = "Next",
        )
    }
}
