package com.example.pomodorocompose.ui.screens.statistics

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.StaticCalendar
import io.github.boguszpawlowski.composecalendar.StaticWeekCalendar
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.EmptySelectionState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel()
){
    val statistics = viewModel.statistic.collectAsStateWithLifecycle(initialValue = null)

    val state = statistics.value?.groupBy { it.datePomodoro }
        ?.mapValues { it.value.count() }

    var dates = emptyList<LocalDate?>()
    var count = emptyList<Int>()

    if(state != null){
        dates = state.keys.toList()
        count = state.values.toList()
    }

    StaticCalendar(
        dayContent = { dayState -> MyDay(dayState) }
    )
    /*
    Column(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyColumn {
            items(dates) { date ->
                Text(text = date.toString())
            }
            items(count) { count ->
                Text(text = count.toString())
            }
        }
    }
     */
}

@Composable
fun MyDay(dayState: DayState<EmptySelectionState>) {
    Column {
        Text(text = dayState.date.toString())
    }
}

