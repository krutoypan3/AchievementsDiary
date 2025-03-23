package ru.aom13.achievementsdiary.model

data class Quote(
    val id: Int,
    val text: String,
    val isPurchased: Boolean = false
)
