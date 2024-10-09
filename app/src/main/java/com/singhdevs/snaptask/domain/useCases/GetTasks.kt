package com.singhdevs.snaptask.domain.useCases

import com.singhdevs.snaptask.domain.OrderType
import com.singhdevs.snaptask.domain.TaskOrder
import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetTasks(
    private val repository: TaskRepository
) {
    operator fun invoke(
        taskOrder: TaskOrder = TaskOrder.Date(OrderType.DESCENDING)
    ): Flow<List<Task>> {
        return repository.getTasks().map { tasks ->
            when(taskOrder.orderType) {
                OrderType.ASCENDING -> {
                    when(taskOrder) {
                        is TaskOrder.Title -> tasks.sortedBy { it.title.lowercase() }
                        is TaskOrder.Status -> tasks.sortedBy { it.status }
                        is TaskOrder.Priority -> tasks.sortedBy { it.priority }
                        is TaskOrder.Date -> tasks.sortedBy { it.date }
                    }
                }
                OrderType.DESCENDING -> {
                    when(taskOrder) {
                        is TaskOrder.Title -> tasks.sortedByDescending { it.title.lowercase() }
                        is TaskOrder.Status -> tasks.sortedByDescending { it.status }
                        is TaskOrder.Priority -> tasks.sortedByDescending { it.priority }
                        is TaskOrder.Date -> tasks.sortedByDescending { it.date }
                    }
                }
            }
        }
    }
}