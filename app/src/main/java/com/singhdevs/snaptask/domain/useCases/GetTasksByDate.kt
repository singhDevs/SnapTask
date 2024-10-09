package com.singhdevs.snaptask.domain.useCases

import com.singhdevs.snaptask.domain.repository.TaskRepository

class GetTasksByDate(
    private val repository: TaskRepository
) {
    suspend operator fun invoke(date: Long){
        repository.getTasksByDate(date)
    }
}