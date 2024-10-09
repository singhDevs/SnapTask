package com.singhdevs.snaptask.presentation

import com.singhdevs.snaptask.domain.OrderType
import com.singhdevs.snaptask.domain.TaskOrder
import com.singhdevs.snaptask.domain.model.Task

data class TasksState(
    val taskList: List<Task> = emptyList(),
    val recentlyDeletedTask: Task? = null,
    val taskOrder: TaskOrder = TaskOrder.Date(OrderType.DESCENDING),
    val isOrderButtonVisible: Boolean = false
)