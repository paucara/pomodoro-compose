package com.example.pomodorocompose.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.Update
import com.example.pomodorocompose.data.model.Statistic
import com.example.pomodorocompose.utils.Converters
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Database(entities = [Statistic::class], version = 1)
@TypeConverters(Converters::class)
abstract class StatisticsDataBase : RoomDatabase() {
    abstract fun statisticsDao(): StatisticsDao

    companion object {
        @Volatile
        private var INSTANCE: StatisticsDataBase? = null

        fun getDatabase(context: Context): StatisticsDataBase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StatisticsDataBase::class.java,
                    "statistics_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

@Dao
interface StatisticsDao {
    @Query("SELECT * FROM statistic")
    fun getAll(): Flow<List<Statistic>>

    @Insert
    fun insert(statistic: Statistic)

    @Delete
    fun delete(statistic: Statistic)

    @Update
    fun update(statistic: Statistic)
}