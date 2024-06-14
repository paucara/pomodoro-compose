package com.example.pomodorocompose.module

import android.content.Context
import com.example.pomodorocompose.model.SettingsDataSource
import com.example.pomodorocompose.model.SettingsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SettingsModule{
    @Provides
    @Singleton
    fun provideSettingsDataSource(
        @ApplicationContext context: Context
    ) : SettingsDataSource {
        return SettingsDataSource(context)
    }
    @Provides
    @Singleton
    fun provideSettingsRepository(
        settingsDataSource: SettingsDataSource
    ): SettingsRepository {
        return SettingsRepository(settingsDataSource)
    }
}