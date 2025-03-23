package ru.aom13.achievementsdiary.data.database

import androidx.room.*
import ru.aom13.achievementsdiary.model.Achievement
import kotlinx.coroutines.flow.Flow

@Dao
interface AchievementDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(achievement: Achievement)

    @Delete
    suspend fun delete(achievement: Achievement)

    @Query("SELECT * FROM achievements ORDER BY date DESC")
    fun getAllAchievements(): Flow<List<Achievement>>

    @Query("SELECT * FROM achievements WHERE id = :id")
    suspend fun getAchievementById(id: Int): Achievement?
}