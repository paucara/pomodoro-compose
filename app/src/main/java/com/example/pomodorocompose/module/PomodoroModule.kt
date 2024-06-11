package com.example.pomodorocompose.module

import android.content.Context
import com.example.pomodorocompose.domain.NotificationManager
import com.example.pomodorocompose.domain.Pomodoro
import com.example.pomodorocompose.model.SettingsDataSource
import com.example.pomodorocompose.model.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ViewModelComponent::class)
class PomodoroModule {
    @Provides
    fun provideSettingsDataSource(
        @ApplicationContext context: Context
    ) : SettingsDataSource {
        return SettingsDataSource(context)
    }
    @Provides
    fun provideSettingsRepository(
        settingsDataSource: SettingsDataSource
    ): SettingsRepository {
        return SettingsRepository(settingsDataSource)
    }
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ) : NotificationManager {
        return NotificationManager(context)
    }
    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
    @Provides
    fun providePomodoro(
        coroutineScope: CoroutineScope,
        notificationManager: NotificationManager,
        settingsRepository: SettingsRepository
    ): Pomodoro {
        return Pomodoro(coroutineScope, notificationManager, settingsRepository)
    }
}