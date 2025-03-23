package ru.aom13.achievementsdiary.ui.screens

/**
 * Экран со списком достижений.
 *
 * Отображение списка достижений (можно использовать LazyColumn).
 * Кнопка для добавления нового достижения.
 * Возможность фильтрации по дате или категориям (если планируется).
 */
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.aom13.achievementsdiary.model.Achievement
import ru.aom13.achievementsdiary.viewmodel.AchievementListViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun AchievementListScreen(
    navController: NavController,
) {
    val application = LocalContext.current.applicationContext as android.app.Application
    val viewModel: AchievementListViewModel = viewModel(
        factory = AchievementListViewModel.AchievementListViewModelFactory(application)
    )
    val achievements by viewModel.achievements.collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("addAchievement") }) {
                Icon(Icons.Filled.Add, "Add new achievement")
            }
        }
    ) { paddingValues ->
        if (achievements.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                Text("Пока нет ни одного достижения. Добавьте первое!")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentPadding = PaddingValues(8.dp)
            ) {
                items(achievements) { achievement ->
                    AchievementItem(achievement = achievement)
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }
    }
}

@Composable
fun AchievementItem(achievement: Achievement) {
    val formattedDate = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault()).format(Date(achievement.date))
    Card(elevation = CardDefaults.cardElevation(defaultElevation = 4.dp), modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = formattedDate, style = MaterialTheme.typography.labelLarge)
            Text(text = achievement.category, style = MaterialTheme.typography.labelMedium)
            Text(text = achievement.description, style = MaterialTheme.typography.labelSmall)
        }
    }
}