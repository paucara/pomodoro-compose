package com.example.pomodorocompose.module

import android.content.Context
import androidx.room.Room
import com.example.pomodorocompose.data.SettingsDataSource
import com.example.pomodorocompose.data.SettingsRepository
import com.example.pomodorocompose.data.StatisticsDao
import com.example.pomodorocompose.data.StatisticsDataBase
import com.example.pomodorocompose.data.StatisticsRepository
import com.example.pomodorocompose.domain.NotificationManager
import com.example.pomodorocompose.domain.Pomodoro
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
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
        settingsRepository: SettingsRepository
    ): Pomodoro {
        return Pomodoro(coroutineScope, settingsRepository)
    }
    @Provides
    @Singleton
    fun provideSettingsRepository(
        settingsDataSource: SettingsDataSource
    ): SettingsRepository {
        return SettingsRepository(settingsDataSource)
    }
    @Provides
    @Singleton
    fun provideSettingsDataSource(
        @ApplicationContext context: Context
    ) : SettingsDataSource {
        return SettingsDataSource(context)
    }

}

@Module
@InstallIn(ActivityComponent::class)
class NotificationModule {
    @Provides
    fun provideNotificationManager(
        @ActivityContext context: Context
    ) : NotificationManager {
        return NotificationManager(context)
    }
}

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideStatisticsDataSource(@ApplicationContext appContext: Context): StatisticsDataBase {
        return Room.databaseBuilder(
            appContext,
            StatisticsDataBase::class.java,
            "statistics_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideDao(statisticsDataBase: StatisticsDataBase) : StatisticsDao{
        return statisticsDataBase.statisticsDao()
    }

    @Provides
    @Singleton
    fun provideStatisticsRepository(statisticsDao : StatisticsDao): StatisticsRepository {
        return StatisticsRepository(statisticsDao)
    }
}

