package com.example.pomodorocompose.ui.screens.statistics.calendar

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.boguszpawlowski.composecalendar.day.DayState
import io.github.boguszpawlowski.composecalendar.selection.SelectionState
import java.time.LocalDate

@Composable
fun <T : SelectionState> MyDayContent(
    state: DayState<T>,
    dateState: Map<LocalDate?, Int>?,
    modifier: Modifier = Modifier,
    onClick: (LocalDate) -> Unit = {},
) {
    val date = state.date
    val selectionState = state.selectionState
    val isSelected = selectionState.isDateSelected(date)

    Card(
        modifier = modifier
            .aspectRatio(1f)
            .padding(2.dp),
        elevation = if (state.isFromCurrentMonth) CardDefaults.cardElevation(defaultElevation = 4.dp) else CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
        border = null,
        colors = if (isSelected) {
            CardDefaults.cardColors(
                contentColor = MaterialTheme.colorScheme.onTertiary,
                containerColor = MaterialTheme.colorScheme.tertiary
            )
        } else {

            if (dateState?.containsKey(state.date) == true) {
                CardDefaults.cardColors(

                    contentColor = MaterialTheme.colorScheme.onSecondary,
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            }else{
                CardDefaults.cardColors(

                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    containerColor = MaterialTheme.colorScheme.primary
                )
            }
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable {
                    onClick(date)
                    selectionState.onDateSelected(date)
                },
            contentAlignment = Alignment.Center
        ) {

            if (state.isCurrentDay) {
                Text(
                    text = date.dayOfMonth.toString(),
                    fontWeight = FontWeight.SemiBold
                )
            }else{
                Text(
                    text = date.dayOfMonth.toString()
                )
            }


        }
    }
}
