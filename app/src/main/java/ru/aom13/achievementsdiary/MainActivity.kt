package ru.aom13.achievementsdiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.aom13.achievementsdiary.ui.screens.AchievementListScreen
import ru.aom13.achievementsdiary.ui.screens.AddAchievementScreen
import ru.aom13.achievementsdiary.ui.screens.PredefinedAchievementsScreen
import ru.aom13.achievementsdiary.ui.screens.QuoteScreen
import ru.aom13.achievementsdiary.ui.theme.AchievementsDiaryTheme
import ru.aom13.achievementsdiary.viewmodel.AchievementListViewModel
import ru.aom13.achievementsdiary.viewmodel.AddAchievementViewModel
import ru.aom13.achievementsdiary.viewmodel.PredefinedAchievementsViewModel

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object AchievementList : Screen("achievementList", "Мои достижения", Icons.AutoMirrored.Filled.List)
    object AddAchievement : Screen("addAchievement", "Добавить", Icons.AutoMirrored.Filled.List)
    object QuoteScreen : Screen("quoteScreen", "Цитаты", Icons.Filled.Star)
    object PredefinedAchievements : Screen("predefinedAchievements", "Готовые", Icons.Filled.CheckCircle)
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AchievementsDiaryTheme {
                val navController = rememberNavController()
                val achievementListViewModel: AchievementListViewModel = viewModel()

                Scaffold(
                    bottomBar = {
                        val navBackStackEntry by navController.currentBackStackEntryAsState()
                        val currentRoute = navBackStackEntry?.destination?.route

                        NavigationBar {
                            NavigationBarItem(
                                icon = { Icon(Screen.AchievementList.icon, contentDescription = Screen.AchievementList.title) },
                                label = { Text(Screen.AchievementList.title) },
                                selected = currentRoute == Screen.AchievementList.route,
                                onClick = { navigateTo(navController, Screen.AchievementList) }
                            )
                            NavigationBarItem(
                                icon = { Icon(Screen.PredefinedAchievements.icon, contentDescription = Screen.PredefinedAchievements.title) },
                                label = { Text(Screen.PredefinedAchievements.title) },
                                selected = currentRoute == Screen.PredefinedAchievements.route,
                                onClick = { navigateTo(navController, Screen.PredefinedAchievements) }
                            )
                            NavigationBarItem(
                                icon = { Icon(Screen.QuoteScreen.icon, contentDescription = Screen.QuoteScreen.title) },
                                label = { Text(Screen.QuoteScreen.title) },
                                selected = currentRoute == Screen.QuoteScreen.route,
                                onClick = { navigateTo(navController, Screen.QuoteScreen) }
                            )
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.AchievementList.route,
                        Modifier.padding(paddingValues)
                    ) {
                        composable(Screen.AchievementList.route) {
                            AchievementListScreen(navController = navController)
                        }
                        composable(Screen.AddAchievement.route) {
                            val application = LocalContext.current.applicationContext as android.app.Application
                            val viewModel: AddAchievementViewModel = viewModel(
                                factory = AddAchievementViewModel.AddAchievementViewModelFactory(application)
                            )
                            AddAchievementScreen(navController = navController, viewModel = viewModel)
                        }
                        composable(Screen.QuoteScreen.route) {
                            QuoteScreen()
                        }
                        composable(Screen.PredefinedAchievements.route) {
                            val application = LocalContext.current.applicationContext as android.app.Application
                            val viewModel: PredefinedAchievementsViewModel = viewModel(
                                factory = PredefinedAchievementsViewModel.PredefinedAchievementsViewModelFactory(application)
                            )
                            PredefinedAchievementsScreen()
                        }
                    }
                }
            }
        }
    }
}

private fun navigateTo(navController: androidx.navigation.NavHostController, screen: Screen) {
    navController.navigate(screen.route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}