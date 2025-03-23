package ru.aom13.achievementsdiary.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.aom13.achievementsdiary.model.CompletedPredefinedAchievement
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedPredefinedAchievementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(completedAchievement: CompletedPredefinedAchievement)

    @Query("SELECT predefinedAchievementId FROM completed_predefined_achievements")
    fun getAllCompletedAchievementIds(): Flow<List<Int>>

    @Query("SELECT * FROM completed_predefined_achievements WHERE predefinedAchievementId = :id")
    suspend fun getCompletedAchievementById(id: Int): CompletedPredefinedAchievement?
}