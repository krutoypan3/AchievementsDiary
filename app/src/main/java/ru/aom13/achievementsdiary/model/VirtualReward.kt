package ru.aom13.achievementsdiary.model

data class VirtualReward(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val isPurchased: Boolean = false
)
