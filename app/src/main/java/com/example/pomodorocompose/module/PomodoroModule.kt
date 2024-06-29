package com.example.pomodorocompose.module

import com.example.pomodorocompose.domain.NotificationManager
import com.example.pomodorocompose.domain.Pomodoro
import com.example.pomodorocompose.data.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PomodoroModule {
    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
    @Provides
    @Singleton
    fun providePomodoro(
        coroutineScope: CoroutineScope,
        notificationManager: NotificationManager,
        settingsRepository: SettingsRepository
    ): Pomodoro {
        return Pomodoro(coroutineScope, notificationManager, settingsRepository)
    }
}