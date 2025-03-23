package ru.aom13.achievementsdiary.domain.usecase

import ru.aom13.achievementsdiary.model.Quote


class GetDailyQuoteUseCase {
    private val freeQuotes = listOf(
        Quote(1, "Успех - это не ключ к счастью. Счастье - это ключ к успеху. Если вы любите то, что делаете, вы будете успешны.", false),
        Quote(2, "Единственный способ сделать великое дело - любить то, что вы делаете.", false),
        Quote(3, "Не ждите идеальных моментов, берите каждый момент и делайте его идеальным.", false),
        // Добавьте больше бесплатных цитат
    )

    operator fun invoke(): Quote {
        return freeQuotes.random() // Возвращает случайную бесплатную цитату
        // В реальном приложении здесь может быть логика для проверки купленных цитат
    }
}