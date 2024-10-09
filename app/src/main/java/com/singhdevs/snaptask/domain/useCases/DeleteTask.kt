package com.singhdevs.snaptask.domain.useCases

import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.repository.TaskRepository

class DeleteTask(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(task: Task){
        repository.deleteTask(task)
    }
}