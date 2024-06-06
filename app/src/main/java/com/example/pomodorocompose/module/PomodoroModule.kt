package com.example.pomodorocompose.module

import com.example.pomodorocompose.domain.Pomodoro
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@Module
@InstallIn(ViewModelComponent::class)
class PomodoroModule {
    @Provides
    fun provideCoroutineScope(): CoroutineScope {
        return CoroutineScope(SupervisorJob())
    }
    @Provides
    fun providePomodoro(coroutineScope: CoroutineScope): Pomodoro {
        return Pomodoro(coroutineScope)
    }
}