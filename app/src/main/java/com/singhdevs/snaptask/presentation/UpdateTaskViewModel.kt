package com.singhdevs.snaptask.presentation

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.singhdevs.snaptask.domain.model.Errors
import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.model.TaskStatus
import com.singhdevs.snaptask.domain.useCases.TaskUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SnapTask"

@HiltViewModel
class UpdateTaskViewModel @Inject constructor(
    private val taskUseCases: TaskUseCases,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    init {
        savedStateHandle.get<Int>("taskId")?.let { taskId ->
            if (taskId != -1) {
                viewModelScope.launch {
                    taskUseCases.getTaskById(taskId)?.also { task ->
                        currTaskId = task.id
                        _taskTitle.value = taskTitle.value.copy(
                            text = task.title,
                            isHintVisible = false
                        )
                        _taskSubtitle.value = taskSubtitle.value.copy(
                            text = task.subtitle,
                            isHintVisible = false
                        )
                        _taskDescription.value = taskDescription.value.copy(
                            text = task.description,
                            isHintVisible = false
                        )
                        _taskPriority.value = task.priority
                        _taskStatus.value = task.status
                        _taskDate.value = task.date
                    }
                }
            }
        }
    }

    private val _taskTitle = mutableStateOf(
        TaskTextFieldState(
            hint = "Enter Task title"
        )
    )
    val taskTitle: State<TaskTextFieldState> = _taskTitle

    private val _taskSubtitle = mutableStateOf(
        TaskTextFieldState(
            hint = "Enter Task subtitle"
        )
    )
    val taskSubtitle: State<TaskTextFieldState> = _taskSubtitle

    private val _taskDescription = mutableStateOf(
        TaskTextFieldState(
            hint = "Enter Task description"
        )
    )
    val taskDescription: State<TaskTextFieldState> = _taskDescription

    private val _taskPriority = mutableStateOf(TaskPriority.LOW)
    val taskPriority: State<TaskPriority> = _taskPriority

    private val _taskStatus = mutableStateOf(TaskStatus.NOT_STARTED)
    val taskStatus: State<TaskStatus> = _taskStatus

    private val _taskDate = mutableLongStateOf(System.currentTimeMillis())
    val taskDate: State<Long> = _taskDate

    private val _eventFlow = MutableSharedFlow<TaskUI>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currTaskId: Int? = null


    fun onEvent(event: UpdateTaskEvent) {
        when (event) {
            is UpdateTaskEvent.DescriptionEntered -> {
                _taskDescription.value = taskDescription.value.copy(
                    text = event.value
                )
            }

            is UpdateTaskEvent.DescriptionFocusChanged -> {
                _taskDescription.value = taskDescription.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskTitle.value.text.isBlank()
                )
            }

            is UpdateTaskEvent.PriorityChanged -> {
                _taskPriority.value = event.priority
            }

            is UpdateTaskEvent.SaveTask -> {
                viewModelScope.launch {
                    try {
                        taskUseCases.addTask(
                            Task(
                                title = taskTitle.value.text,
                                subtitle = taskSubtitle.value.text,
                                description = taskDescription.value.text,
                                priority = taskPriority.value,
                                status = taskStatus.value,
                                date = taskDate.value,
                                id = currTaskId,
                            )
                        )
                        _eventFlow.emit(TaskUI.SaveTask)
                    } catch (e: Errors.FieldsNotFilledException) {
                        Log.e(TAG, "onEvent: ${e.message}")
                        _eventFlow.emit(
                            TaskUI.DisplaySnackbar(
                                message = e.message ?: "Unable to save the Task at the moment."
                            )
                        )
                    }
                }
            }

            is UpdateTaskEvent.StatusChanged -> {
                _taskStatus.value = event.state
            }

            is UpdateTaskEvent.SubtitleEntered -> {
                _taskSubtitle.value = taskSubtitle.value.copy(
                    text = event.value
                )
            }

            is UpdateTaskEvent.SubtitleFocusChanged -> {
                _taskSubtitle.value = taskSubtitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskTitle.value.text.isBlank()
                )
            }

            is UpdateTaskEvent.TitleEntered -> {
                _taskTitle.value = taskTitle.value.copy(
                    text = event.value
                )
            }

            is UpdateTaskEvent.TitleFocusChanged -> {
                _taskTitle.value = taskTitle.value.copy(
                    isHintVisible = !event.focusState.isFocused && taskTitle.value.text.isBlank()
                )
            }

            is UpdateTaskEvent.DateChanged -> {
                _taskDate.value = event.date
            }

            UpdateTaskEvent.CancelTask -> {
                viewModelScope.launch {
                    _eventFlow.emit(TaskUI.CancelTask)
                }
            }
        }
    }

}