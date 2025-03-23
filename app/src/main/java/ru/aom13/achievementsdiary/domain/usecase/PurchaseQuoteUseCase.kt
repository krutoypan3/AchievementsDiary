package ru.aom13.achievementsdiary.domain.usecase


class PurchaseQuoteUseCase {
    // В реальном приложении здесь будет логика для взаимодействия с Google Play Billing
    // и обновления состояния Quote на "куплено"
    operator fun invoke(quoteId: Int): Boolean {
        // Пока просто возвращаем true для примера
        println("Покупка цитаты с ID $quoteId")
        return true
    }
}