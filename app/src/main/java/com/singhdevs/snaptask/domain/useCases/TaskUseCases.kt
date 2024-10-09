package com.singhdevs.snaptask.domain.useCases

data class TaskUseCases(
    val getTasks: GetTasks,
    val getTasksByPriority: GetTasksByPriority,
    val getTasksByStatus: GetTasksByStatus,
    val getTasksByDate: GetTasksByDate,
    val getTaskById: GetTaskById,
    val addTask: AddTask,
    val deleteTask: DeleteTask
)