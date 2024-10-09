package com.singhdevs.snaptask.domain.repository

import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getTasks(): Flow<List<Task>>
    fun getTasksByPriority(priority: TaskPriority): Flow<List<Task>>
    fun getTasksByStatus(status: TaskStatus): Flow<List<Task>>
    fun getTasksByDate(date: Long): Flow<List<Task>>
    suspend fun getTaskById(id: Int): Task?
    suspend fun addTask(task: Task)
    suspend fun deleteTask(task: Task)
}