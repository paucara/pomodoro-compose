package com.example.pomodorocompose.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataSource @Inject constructor(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val pomodoroDuration: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[POMODORO_DURATION] ?: 25
        }
    val longRestDuration: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[LONG_REST_DURATION] ?: 15
        }
    val shortRestDuration: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[SHORT_REST_DURATION] ?: 5
        }
    val pomodoroLoops: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[POMODORO_LOOPS] ?: 4
        }

    suspend fun setPomodoroDuration(value : Int){
        context.dataStore.edit { preferences ->
            val currentValue = preferences[POMODORO_DURATION] ?: 25
            preferences[POMODORO_DURATION] = value
        }
    }
    suspend fun setLongRestDuration(value : Int){
        context.dataStore.edit { preferences ->
            val currentValue = preferences[LONG_REST_DURATION] ?: 15
            preferences[LONG_REST_DURATION] = value
        }
    }
    suspend fun setShortRestDuration(value : Int){
        context.dataStore.edit { preferences ->
            val currentValue = preferences[SHORT_REST_DURATION] ?: 5
            preferences[SHORT_REST_DURATION] = value
        }
    }
    suspend fun setPomodoroLoops(value : Int){
        context.dataStore.edit { preferences ->
            val currentValue = preferences[POMODORO_LOOPS] ?: 4
            preferences[POMODORO_LOOPS] = value
        }
    }

    suspend fun getSettings() : PomodoroSettings{
        return PomodoroSettings(
            context.dataStore.data.first()[POMODORO_DURATION] ?: 25,
            context.dataStore.data.first()[LONG_REST_DURATION] ?: 15,
            context.dataStore.data.first()[SHORT_REST_DURATION] ?: 5,
            context.dataStore.data.first()[POMODORO_LOOPS] ?: 4
        )
    }

    companion object{
        val POMODORO_DURATION = intPreferencesKey("pomodoro_duration")
        val LONG_REST_DURATION = intPreferencesKey("long_rest_duration")
        val SHORT_REST_DURATION = intPreferencesKey("short_rest_duration")
        val POMODORO_LOOPS = intPreferencesKey("pomodoro_loops")
    }
}

