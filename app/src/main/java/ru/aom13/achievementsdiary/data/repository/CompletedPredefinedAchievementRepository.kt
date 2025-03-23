package ru.aom13.achievementsdiary.data.repository

import ru.aom13.achievementsdiary.data.database.CompletedPredefinedAchievementDao
import ru.aom13.achievementsdiary.model.CompletedPredefinedAchievement
import kotlinx.coroutines.flow.Flow

class CompletedPredefinedAchievementRepository(private val completedPredefinedAchievementDao: CompletedPredefinedAchievementDao) {

    val allCompletedAchievementIds: Flow<List<Int>> = completedPredefinedAchievementDao.getAllCompletedAchievementIds()

    suspend fun insertCompletedAchievement(predefinedAchievementId: Int) {
        val completedAchievement = CompletedPredefinedAchievement(
            predefinedAchievementId = predefinedAchievementId,
            completionDate = System.currentTimeMillis()
        )
        completedPredefinedAchievementDao.insert(completedAchievement)
    }

    suspend fun getCompletedAchievementById(id: Int): CompletedPredefinedAchievement? {
        return completedPredefinedAchievementDao.getCompletedAchievementById(id)
    }
}