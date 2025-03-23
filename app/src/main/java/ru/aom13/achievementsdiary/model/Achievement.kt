package ru.aom13.achievementsdiary.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class Achievement(
    @PrimaryKey val id: Int? = null, // Для Room Database
    val date: Long, // Unix timestamp
    val category: String,
    val description: String
)
