package ru.aom13.achievementsdiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ru.aom13.achievementsdiary.data.database.AchievementDatabase
import ru.aom13.achievementsdiary.data.repository.CompletedPredefinedAchievementRepository
import ru.aom13.achievementsdiary.model.PredefinedAchievementItem
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.aom13.achievementsdiary.model.PredefinedAchievements

class PredefinedAchievementsViewModel(application: Application) : AndroidViewModel(application) {

    private val completedPredefinedAchievementRepository: CompletedPredefinedAchievementRepository

    private val _predefinedAchievements = MutableStateFlow<List<PredefinedAchievementItem>>(emptyList())
    val predefinedAchievements: StateFlow<List<PredefinedAchievementItem>> = _predefinedAchievements.asStateFlow()

    init {
        val database = AchievementDatabase.getDatabase(application)
        completedPredefinedAchievementRepository = CompletedPredefinedAchievementRepository(database.completedPredefinedAchievementDao())
        loadPredefinedAchievementsWithStatus()
    }

    private fun loadPredefinedAchievementsWithStatus() {
        val predefinedList = PredefinedAchievements.achievements
        completedPredefinedAchievementRepository.allCompletedAchievementIds.onEach { completedIds ->
            val items = predefinedList.map { achievement ->
                PredefinedAchievementItem(achievement, completedIds.contains(achievement.id))
            }
            _predefinedAchievements.value = items
        }.launchIn(viewModelScope)
    }

    fun onAchievementCompletionToggled(achievementId: Int, isChecked: Boolean) {
        viewModelScope.launch {
            if (isChecked) {
                completedPredefinedAchievementRepository.insertCompletedAchievement(achievementId)
            } else {
                // Implement logic to remove from completed if needed
                // completedPredefinedAchievementRepository.deleteCompletedAchievement(achievementId)
            }
        }
    }

    class PredefinedAchievementsViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(PredefinedAchievementsViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return PredefinedAchievementsViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}