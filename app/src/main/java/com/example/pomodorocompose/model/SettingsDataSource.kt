package com.example.pomodorocompose.model

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SettingsDataSource @Inject constructor(private val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    val pomodoroDuration: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[POMODORO_DURATION] ?: 25000L
        }
    val longRestDuration: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[LONG_REST_DURATION] ?: 15000L
        }
    val shortRestDuration: Flow<Long> = context.dataStore.data
        .map { preferences ->
            preferences[SHORT_REST_DURATION] ?: 5000L
        }
    val pomodoroLoops: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[POMODORO_LOOPS] ?: 4
        }

    suspend fun setPomodoroDuration(value : Long){
        context.dataStore.edit { settings ->
            settings[POMODORO_DURATION] = value
        }
    }
    suspend fun setLongRestDuration(value : Long){
        context.dataStore.edit { settings ->
            settings[LONG_REST_DURATION] = value
        }
    }
    suspend fun setShortRestDuration(value : Long){
        context.dataStore.edit { settings ->
            settings[SHORT_REST_DURATION] = value
        }
    }
    suspend fun setPomodoroLoops(value : Int){
        context.dataStore.edit { settings ->
            settings[POMODORO_LOOPS] = value
        }
    }
    companion object{
        val POMODORO_DURATION = longPreferencesKey("pomodoro_duration")
        val LONG_REST_DURATION = longPreferencesKey("long_rest_duration")
        val SHORT_REST_DURATION = longPreferencesKey("short_rest_duration")
        val POMODORO_LOOPS = intPreferencesKey("pomodoro_loops")
    }
}

