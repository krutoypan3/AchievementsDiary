package ru.aom13.achievementsdiary.data.repository

import ru.aom13.achievementsdiary.data.database.AchievementDao
import ru.aom13.achievementsdiary.model.Achievement
import kotlinx.coroutines.flow.Flow

class AchievementRepository(private val achievementDao: AchievementDao) {

    val allAchievements: Flow<List<Achievement>> = achievementDao.getAllAchievements()

    suspend fun insertAchievement(achievement: Achievement) {
        achievementDao.insert(achievement)
    }

    suspend fun deleteAchievement(achievement: Achievement) {
        achievementDao.delete(achievement)
    }

    suspend fun getAchievementById(id: Int): Achievement? {
        return achievementDao.getAchievementById(id)
    }
}