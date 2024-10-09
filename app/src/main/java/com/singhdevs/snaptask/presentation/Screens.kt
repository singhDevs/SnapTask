package com.singhdevs.snaptask.presentation

sealed class Screen(val route: String) {
    object TasksScreen: Screen("tasks_screen")
    object UpdateTaskScreen: Screen("update_task_screen")
}