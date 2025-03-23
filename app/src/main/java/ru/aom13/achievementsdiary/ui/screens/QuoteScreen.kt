package ru.aom13.achievementsdiary.ui.screens

/**
 * Экран с вдохновляющими цитатами (доступ к купленным и бесплатным).
 *
 * Отображение текущей цитаты.
 * Возможность пролистывания цитат (если куплено несколько).
 */

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.aom13.achievementsdiary.viewmodel.QuoteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuoteScreen() {
    val quoteViewModel: QuoteViewModel = viewModel()
    val currentQuote by quoteViewModel.currentQuote

    Scaffold(
        topBar = { TopAppBar(title = { Text("Вдохновляющая цитата") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = currentQuote.text, style = MaterialTheme.typography.bodyLarge)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { quoteViewModel.getNewQuote() }) {
                Text("Показать другую цитату")
            }
            // Кнопка для покупки цитат (реализация Google Play Billing)
            // Пример:
            if (!currentQuote.isPurchased) {
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { quoteViewModel.purchaseQuote(currentQuote.id) }) {
                    Text("Купить эту цитату") // В реальности может быть более сложный UI
                }
            } else {
                Spacer(modifier = Modifier.height(16.dp))
                Text("Эта цитата уже куплена!", style = MaterialTheme.typography.labelLarge)
            }
        }
    }
}