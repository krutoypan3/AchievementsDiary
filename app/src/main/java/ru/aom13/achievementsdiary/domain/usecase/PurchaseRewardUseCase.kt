package ru.aom13.achievementsdiary.domain.usecase

class PurchaseRewardUseCase {
    // Аналогично PurchaseQuoteUseCase, здесь будет взаимодействие с Google Play Billing
    operator fun invoke(rewardId: Int): Boolean {
        println("Покупка награды с ID $rewardId")
        return true
    }
}