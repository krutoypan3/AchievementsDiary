package ru.aom13.achievementsdiary.model

import ru.aom13.achievementsdiary.R

data class PredefinedAchievement(
    val id: Int,
    val name: String,
    val description: String,
    val rarity: Rarity,
    val imageResId: Int // Add this field
)

enum class Rarity {
    COMMON,
    RARE,
    ULTRA_RARE,
    MYSTICAL,
    LEGENDARY
}

object PredefinedAchievements {
    val achievements = listOf(
        PredefinedAchievement(1, "Первые шаги", "Запишите свое первое достижение.", Rarity.COMMON, R.drawable.pervie_shagi),
        PredefinedAchievement(2, "Ежедневная рутина", "Записывайте достижения 5 дней подряд.", Rarity.COMMON, R.drawable.rutina),
        PredefinedAchievement(3, "Целеустремленность", "Запишите 10 достижений в категории 'Работа'.", Rarity.COMMON, R.drawable.celi),
        PredefinedAchievement(4, "Новое хобби", "Запишите 5 достижений в категории 'Хобби'.", Rarity.COMMON, R.drawable.test),
        PredefinedAchievement(5, "Успех в учебе", "Запишите 7 достижений в категории 'Учеба'.", Rarity.COMMON, R.drawable.test),
        PredefinedAchievement(6, "Редкий успех", "Запишите достижение, которое вы считаете особенно редким.", Rarity.RARE, R.drawable.test),
        PredefinedAchievement(7, "Недельный отчет", "Записывайте достижения 7 дней подряд.", Rarity.RARE, R.drawable.test),
        PredefinedAchievement(8, "Разносторонность", "Запишите достижения в 3 разных категориях за одну неделю.", Rarity.RARE, R.drawable.test),
        PredefinedAchievement(9, "Ультра продуктивность", "Запишите 3 достижения за один день.", Rarity.ULTRA_RARE, R.drawable.test),
        PredefinedAchievement(10, "Мастер своего дела", "Запишите 20 достижений в одной категории.", Rarity.ULTRA_RARE, R.drawable.test),
        PredefinedAchievement(11, "Мистическое открытие", "Совершите что-то неожиданное и запишите это как достижение.", Rarity.MYSTICAL, R.drawable.test),
        PredefinedAchievement(12, "Легендарный прорыв", "Достигните выдающегося результата, которым вы действительно гордитесь.", Rarity.LEGENDARY, R.drawable.test)
    )
}
