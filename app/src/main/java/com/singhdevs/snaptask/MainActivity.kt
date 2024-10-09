package com.singhdevs.snaptask

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.singhdevs.snaptask.domain.model.TaskStatus
import com.singhdevs.snaptask.presentation.Screen
import com.singhdevs.snaptask.presentation.TasksScreen
import com.singhdevs.snaptask.presentation.UpdateTaskScreen
import com.singhdevs.snaptask.ui.theme.SnapTaskTheme
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SnapTask"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkTheme by remember { mutableStateOf(false) }

            SnapTaskTheme(darkTheme = isDarkTheme) {
                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.TasksScreen.route
                    ) {
                        composable(route = Screen.TasksScreen.route) {
                            TasksScreen(navController = navController){
                                isDarkTheme = !isDarkTheme
                                Log.i(TAG, "onCreate dark theme: $isDarkTheme")
                            }
                        }
                        composable(
                            route = Screen.UpdateTaskScreen.route +
                                    "?taskId={taskId}&taskStatus={taskStatus}",
                            arguments = listOf(
                                navArgument(
                                    name = "taskId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "taskStatus"
                                ) {
                                    type = NavType.EnumType(TaskStatus::class.java)
                                    defaultValue = TaskStatus.NOT_STARTED
                                },
                            )
                        ) {
                            val status =
                                it.arguments?.getSerializable("taskStatus", TaskStatus::class.java)
                                    ?: TaskStatus.NOT_STARTED
                            UpdateTaskScreen(
                                navController = navController,
                                taskStatus = status
                            )
                        }
                    }
                }
            }
        }
    }
}