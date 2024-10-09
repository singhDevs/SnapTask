package com.singhdevs.snaptask.presentation

sealed class TaskUI {
    data class DisplaySnackbar(val message: String): TaskUI()
    object SaveTask: TaskUI()
    object CancelTask: TaskUI()
}