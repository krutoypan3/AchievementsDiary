package ru.aom13.achievementsdiary.domain.usecase

import kotlinx.coroutines.flow.Flow
import ru.aom13.achievementsdiary.data.repository.AchievementRepository
import ru.aom13.achievementsdiary.model.Achievement

class GetAchievementsUseCase(private val repository: AchievementRepository) {
    operator fun invoke(): Flow<List<Achievement>> {
        return repository.allAchievements
    }
}