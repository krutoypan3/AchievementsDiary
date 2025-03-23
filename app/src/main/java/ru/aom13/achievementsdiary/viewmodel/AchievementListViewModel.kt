package ru.aom13.achievementsdiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ru.aom13.achievementsdiary.data.database.AchievementDatabase
import ru.aom13.achievementsdiary.data.repository.AchievementRepository
import ru.aom13.achievementsdiary.domain.usecase.GetAchievementsUseCase
import ru.aom13.achievementsdiary.model.Achievement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AchievementListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AchievementRepository
    private val getAchievementsUseCase: GetAchievementsUseCase

    init {
        val achievementDao = AchievementDatabase.getDatabase(application).achievementDao()
        repository = AchievementRepository(achievementDao)
        getAchievementsUseCase = GetAchievementsUseCase(repository)
    }

    val achievements: Flow<List<Achievement>> = getAchievementsUseCase()

    fun deleteAchievement(achievement: Achievement) {
        viewModelScope.launch {
            repository.deleteAchievement(achievement)
        }
    }

    class AchievementListViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AchievementListViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AchievementListViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}