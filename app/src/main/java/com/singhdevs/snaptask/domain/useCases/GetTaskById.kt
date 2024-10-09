package com.singhdevs.snaptask.domain.useCases

import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.repository.TaskRepository

class GetTaskById(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(id: Int): Task?{
        return repository.getTaskById(id)
    }
}