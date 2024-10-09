package com.singhdevs.snaptask.data.repository

import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.model.TaskStatus
import com.singhdevs.snaptask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MockTaskRepository : TaskRepository {

    private val tasks = mutableListOf<Task>()

    override fun getTasks(): Flow<List<Task>> {
        return flow { emit(tasks) }
    }

    override fun getTasksByPriority(priority: TaskPriority): Flow<List<Task>> {
        return flow {
            val filteredTasks = tasks.filter { it.priority == priority }
            emit(filteredTasks)
        }
    }

    override fun getTasksByStatus(status: TaskStatus): Flow<List<Task>> {
        return flow {
            val filteredTasks = tasks.filter { it.status == status }
            emit(filteredTasks)
        }
    }

    override fun getTasksByDate(date: Long): Flow<List<Task>> {
        return flow {
            val filteredTasks = tasks.filter { it.date == date }
            emit(filteredTasks)
        }
    }

    override suspend fun getTaskById(id: Int): Task? {
        return tasks.find { it.id == id }
    }

    override suspend fun addTask(task: Task) {
        tasks.add(task)
    }

    override suspend fun deleteTask(task: Task) {
        tasks.remove(task)
    }
}