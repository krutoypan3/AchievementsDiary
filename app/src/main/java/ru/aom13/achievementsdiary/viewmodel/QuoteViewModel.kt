package ru.aom13.achievementsdiary.viewmodel


import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.aom13.achievementsdiary.domain.usecase.GetDailyQuoteUseCase
import ru.aom13.achievementsdiary.domain.usecase.PurchaseQuoteUseCase
import ru.aom13.achievementsdiary.model.Quote
import kotlinx.coroutines.launch

class QuoteViewModel : ViewModel() {

    private val getDailyQuoteUseCase = GetDailyQuoteUseCase()
    private val purchaseQuoteUseCase = PurchaseQuoteUseCase()

    private val _currentQuote = mutableStateOf(getDailyQuoteUseCase())
    val currentQuote: State<Quote> = _currentQuote

    fun getNewQuote() {
        _currentQuote.value = getDailyQuoteUseCase()
    }

    fun purchaseQuote(quoteId: Int) {
        viewModelScope.launch {
            val isPurchased = purchaseQuoteUseCase(quoteId)
            if (isPurchased) {
                // Обновите состояние цитаты как купленную (в реальном приложении)
                val updatedQuote = _currentQuote.value.copy(isPurchased = true)
                _currentQuote.value = updatedQuote
                println("Цитата с ID $quoteId успешно куплена")
            } else {
                println("Не удалось купить цитату с ID $quoteId")
            }
        }
    }
}