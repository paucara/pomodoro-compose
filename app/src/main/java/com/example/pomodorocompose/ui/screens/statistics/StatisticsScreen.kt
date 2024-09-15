package com.example.pomodorocompose.ui.screens.statistics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.boguszpawlowski.composecalendar.SelectableCalendar
import io.github.boguszpawlowski.composecalendar.rememberSelectableCalendarState
import io.github.boguszpawlowski.composecalendar.selection.DynamicSelectionState
import java.time.LocalDate

@Composable
fun StatisticsScreen(
    viewModel: StatisticsViewModel = hiltViewModel()
){
    val statistics = viewModel.statistic.collectAsStateWithLifecycle(initialValue = null)

    val state = statistics.value?.groupBy { it.datePomodoro }
        ?.mapValues { it.value.count() }

    SelectableCalendarSample(state)
}



@Composable
fun SelectableCalendarSample(state: Map<LocalDate?, Int>?) {
    val calendarState = rememberSelectableCalendarState()
    Column(
        modifier = Modifier.padding(10.dp)
    ) {
        SelectableCalendar(
            calendarState = calendarState,
            monthHeader = { MyMonthHeader(it) },
            daysOfWeekHeader = { MyDaysOfWeekHeader(it) }
        )
        SelectionData(selectionState = calendarState.selectionState, state)
    }
}

@Composable
private fun SelectionData(
    selectionState: DynamicSelectionState,
    state : Map<LocalDate?, Int>?
) {
    selectionState.selection.forEach{
        if(state?.containsKey(it) == true){
            Text(
                text = "Completed pomodoros : ${state[selectionState.selection.first()]}",
                modifier = Modifier.padding(top = 30.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }else{
            Text(
                text = "No pomodoros" ,
                modifier = Modifier.padding(top = 30.dp),
                style = MaterialTheme.typography.labelLarge
            )
        }
    }
}

