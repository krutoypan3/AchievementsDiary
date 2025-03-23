package ru.aom13.achievementsdiary.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import ru.aom13.achievementsdiary.data.database.AchievementDatabase
import ru.aom13.achievementsdiary.data.repository.AchievementRepository
import ru.aom13.achievementsdiary.domain.usecase.AddAchievementUseCase
import kotlinx.coroutines.launch

class AddAchievementViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AchievementRepository
    private val addAchievementUseCase: AddAchievementUseCase

    init {
        val achievementDao = AchievementDatabase.getDatabase(application).achievementDao()
        repository = AchievementRepository(achievementDao)
        addAchievementUseCase = AddAchievementUseCase(repository)
    }

    fun addAchievement(date: Long, category: String, description: String) {
        viewModelScope.launch {
            addAchievementUseCase(date, category, description)
        }
    }

    class AddAchievementViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
        override fun <T : androidx.lifecycle.ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AddAchievementViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return AddAchievementViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}