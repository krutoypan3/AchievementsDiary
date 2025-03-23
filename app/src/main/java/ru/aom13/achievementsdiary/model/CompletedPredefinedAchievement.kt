package ru.aom13.achievementsdiary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "completed_predefined_achievements")
data class CompletedPredefinedAchievement(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val predefinedAchievementId: Int,
    val completionDate: Long // Unix timestamp
)