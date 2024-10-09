package com.singhdevs.snaptask.domain.useCases

import com.singhdevs.snaptask.domain.model.TaskStatus
import com.singhdevs.snaptask.domain.repository.TaskRepository

class GetTasksByStatus(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(status: TaskStatus){
        repository.getTasksByStatus(status)
    }
}