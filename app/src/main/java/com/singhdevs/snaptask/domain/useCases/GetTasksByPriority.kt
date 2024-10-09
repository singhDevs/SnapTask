package com.singhdevs.snaptask.domain.useCases

import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.repository.TaskRepository

class GetTasksByPriority(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(priority: TaskPriority){
        repository.getTasksByPriority(priority)
    }
}