package com.example.pomodorocompose.di

import android.content.Context
import androidx.room.Room
import com.example.pomodorocompose.data.settings.DefaultSettingsRepository
import com.example.pomodorocompose.data.settings.SettingsDataSource
import com.example.pomodorocompose.data.settings.SettingsRepository
import com.example.pomodorocompose.data.statistics.DefaultStatisticsRepository
import com.example.pomodorocompose.data.statistics.StatisticsDao
import com.example.pomodorocompose.data.statistics.StatisticsDataBase
import com.example.pomodorocompose.data.statistics.StatisticsRepository
import com.example.pomodorocompose.domain.NotificationManager
import com.example.pomodorocompose.domain.Pomodoro
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        settingsRepository: SettingsRepository,
        statisticsRepository: StatisticsRepository
    ): Pomodoro {
        return Pomodoro(coroutineScope, settingsRepository, statisticsRepository)
    }

    @Provides
    @Singleton
    fun provideSettingsDataSource(
        @ApplicationContext context: Context
    ) : SettingsDataSource {
        return SettingsDataSource(context)
    }
    @Provides
    @Singleton
    fun provideStatisticsDataBase(@ApplicationContext appContext: Context): StatisticsDataBase {
        return Room.databaseBuilder(
            appContext,
            StatisticsDataBase::class.java,
            "statistics_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(statisticsDataBase: StatisticsDataBase) : StatisticsDao {
        return statisticsDataBase.statisticsDao()
    }

    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context
    ) : NotificationManager {
        return NotificationManager(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class StatisticsModule{
    @Binds
    abstract fun bindStatisticsRepository(
        defaultStatisticsRepository: DefaultStatisticsRepository
    ) : StatisticsRepository
}

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsModule{
    @Binds
    abstract fun bindSettingsRepository(
        defaultSettingsRepository: DefaultSettingsRepository
    ) : SettingsRepository
}
