package ru.aom13.achievementsdiary.data.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.aom13.achievementsdiary.model.Achievement
import ru.aom13.achievementsdiary.model.CompletedPredefinedAchievement

@Database(entities = [Achievement::class, CompletedPredefinedAchievement::class], version = 2, exportSchema = false) // Увеличьте версию базы данных
abstract class AchievementDatabase : RoomDatabase() {

    abstract fun achievementDao(): AchievementDao
    abstract fun completedPredefinedAchievementDao(): CompletedPredefinedAchievementDao // Добавьте DAO для выполненных достижений

    companion object {
        @Volatile
        private var INSTANCE: AchievementDatabase? = null

        fun getDatabase(context: Context): AchievementDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AchievementDatabase::class.java,
                    "achievement_database"
                )
                    .fallbackToDestructiveMigration() // Рекомендовано для разработки
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}