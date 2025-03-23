package ru.aom13.achievementsdiary.ui.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import ru.aom13.achievementsdiary.model.PredefinedAchievement
import ru.aom13.achievementsdiary.model.Rarity
import ru.aom13.achievementsdiary.viewmodel.PredefinedAchievementsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PredefinedAchievementsScreen() {
    val viewModel: PredefinedAchievementsViewModel = viewModel()
    val predefinedAchievementItems by viewModel.predefinedAchievements.collectAsState(initial = emptyList())
    val completedCount = predefinedAchievementItems.count { it.isCompleted }
    val totalCount = predefinedAchievementItems.size

    Scaffold(
        topBar = { TopAppBar(title = { Text("Предопределенные достижения") }) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
        ) {
            Text(
                text = "Выполнено: $completedCount из $totalCount",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            LazyVerticalGrid(
                columns = GridCells.Fixed(2), // Display 2 cards per row
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(predefinedAchievementItems) { item ->
                    PredefinedAchievementItem(
                        predefinedAchievement = item.achievement,
                        isCompleted = item.isCompleted,
                        onCompletionToggled = { isChecked ->
                            viewModel.onAchievementCompletionToggled(item.achievement.id, isChecked)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun PredefinedAchievementItem(
    predefinedAchievement: PredefinedAchievement,
    isCompleted: Boolean,
    onCompletionToggled: (Boolean) -> Unit
) {
    val backgroundColor = when (predefinedAchievement.rarity) {
        Rarity.COMMON -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.1f)
        Rarity.RARE -> MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        Rarity.ULTRA_RARE -> Color(0xFFE0BBE4).copy(alpha = 0.4f)
        Rarity.MYSTICAL -> Color(0xFFFFFACD).copy(alpha = 0.4f)
        Rarity.LEGENDARY -> Color(0xFFFFDAB9).copy(alpha = 0.4f)
    }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f),
        colors = CardDefaults.cardColors(containerColor = backgroundColor)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = predefinedAchievement.imageResId),
                contentDescription = predefinedAchievement.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(if (isCompleted) 0.9f else 0.3f)
            )
            Surface(
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.1f),
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = predefinedAchievement.name,
                            style = MaterialTheme.typography.headlineSmall,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Text(
                            text = predefinedAchievement.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val rarityColor = when (predefinedAchievement.rarity) {
                                Rarity.COMMON -> Color.Gray
                                Rarity.RARE -> Color.Blue
                                Rarity.ULTRA_RARE -> Color.Magenta
                                Rarity.MYSTICAL -> Color.Yellow
                                Rarity.LEGENDARY -> Color.Red
                            }
                            Icon(
                                imageVector = Icons.Filled.Star,
                                contentDescription = predefinedAchievement.rarity.toString(),
                                tint = rarityColor,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = predefinedAchievement.rarity.toString(),
                                style = MaterialTheme.typography.bodySmall,
                                color = rarityColor
                            )
                        }
                    }
                    Checkbox(
                        checked = isCompleted,
                        onCheckedChange = onCompletionToggled
                    )
                }
            }
        }
    }
}