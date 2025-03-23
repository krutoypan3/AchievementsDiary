package ru.aom13.achievementsdiary.domain.usecase

import ru.aom13.achievementsdiary.data.repository.AchievementRepository
import ru.aom13.achievementsdiary.model.Achievement

class AddAchievementUseCase(private val repository: AchievementRepository) {
    suspend operator fun invoke(date: Long, category: String, description: String) {
        repository.insertAchievement(Achievement(date = date, category = category, description = description))
    }
}