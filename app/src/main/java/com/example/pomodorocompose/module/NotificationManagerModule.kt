package com.example.pomodorocompose.module

import android.content.Context
import com.example.pomodorocompose.domain.NotificationManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityRetainedComponent::class)
class NotificationManagerModule {
     @Provides
     fun provideNotificationManager(
          @ApplicationContext context: Context
     ) : NotificationManager {
         return NotificationManager(context)
     }
}