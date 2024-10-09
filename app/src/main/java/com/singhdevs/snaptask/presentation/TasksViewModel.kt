package com.singhdevs.snaptask.presentation

import androidx.lifecycle.ViewModel
import com.singhdevs.snaptask.domain.useCases.TaskUseCases
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.singhdevs.snaptask.domain.OrderType
import com.singhdevs.snaptask.domain.TaskOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases
) : ViewModel() {

    private val _state = mutableStateOf(TasksState())
    val state: State<TasksState> = _state

    private val _darkThemeState = mutableStateOf(false)
    val darkThemeState: State<Boolean> = _darkThemeState

    private var getTasksJob: Job? = null

    init {
        getTasks(TaskOrder.Date(OrderType.DESCENDING))
    }

    fun onEvent(event: TasksEvent) {
        when (event) {
            is TasksEvent.DeleteTask -> {
                viewModelScope.launch {
                    taskUseCases.deleteTask(event.task)
                }
            }

            is TasksEvent.RestoreTask -> {
                viewModelScope.launch {
                    taskUseCases.addTask(_state.value.recentlyDeletedTask ?: return@launch)
                    _state.value = _state.value.copy(recentlyDeletedTask = null)
                }
            }

            is TasksEvent.Order -> {
                if (state.value.taskOrder::class == event.taskOrder::class &&
                    state.value.taskOrder.orderType == event.taskOrder.orderType
                ) return
                else getTasks(event.taskOrder)
            }

            TasksEvent.ToggleOrderButtonVisibility ->
                _state.value = state.value.copy(
                    isOrderButtonVisible = !state.value.isOrderButtonVisible
                )

            TasksEvent.DarkThemeToggled -> _darkThemeState.value = !_darkThemeState.value
        }
    }

    private fun getTasks(taskOrder: TaskOrder) {
        getTasksJob?.cancel()
        getTasksJob = taskUseCases.getTasks(taskOrder)
            .onEach { tasks ->
                _state.value = state.value.copy(
                    taskList = tasks,
                    taskOrder = taskOrder
                )
            }
            .launchIn(viewModelScope)
    }
}