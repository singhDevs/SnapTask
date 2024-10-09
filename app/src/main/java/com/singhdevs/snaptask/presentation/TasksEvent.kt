package com.singhdevs.snaptask.presentation

import com.singhdevs.snaptask.domain.TaskOrder
import com.singhdevs.snaptask.domain.model.Task

sealed class TasksEvent {
    data class DeleteTask(val task: Task): TasksEvent()
    object RestoreTask: TasksEvent()
    data class Order(val taskOrder: TaskOrder): TasksEvent()
    object ToggleOrderButtonVisibility: TasksEvent()
    object DarkThemeToggled: TasksEvent()
}