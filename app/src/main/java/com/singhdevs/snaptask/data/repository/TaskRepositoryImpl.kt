package com.singhdevs.snaptask.data.repository

import com.singhdevs.snaptask.data.dataSource.TaskDAO
import com.singhdevs.snaptask.domain.repository.TaskRepository
import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.model.TaskStatus
import kotlinx.coroutines.flow.Flow

class TaskRepositoryImpl(
    private val dao: TaskDAO
): TaskRepository {

    override fun getTasks(): Flow<List<Task>> = dao.getTasks()

    override fun getTasksByPriority(priority: TaskPriority): Flow<List<Task>> = dao.getTasksByPriority(priority.value)

    override fun getTasksByStatus(status: TaskStatus): Flow<List<Task>> = dao.getTasksByStatus(status.value)

    override fun getTasksByDate(date: Long): Flow<List<Task>> = dao.getTasksByDate(date)

    override suspend fun getTaskById(id: Int): Task? = dao.getTaskById(id)

    override suspend fun addTask(task: Task) = dao.addTask(task)

    override suspend fun deleteTask(task: Task) = dao.deleteTask(task)

}