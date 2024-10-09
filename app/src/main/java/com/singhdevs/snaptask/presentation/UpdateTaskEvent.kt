package com.singhdevs.snaptask.presentation

import androidx.compose.ui.focus.FocusState
import com.singhdevs.snaptask.domain.model.TaskPriority
import com.singhdevs.snaptask.domain.model.TaskStatus

sealed class UpdateTaskEvent {
    data class TitleEntered(val value: String): UpdateTaskEvent()
    data class SubtitleEntered(val value: String): UpdateTaskEvent()
    data class DescriptionEntered(val value: String): UpdateTaskEvent()
    data class TitleFocusChanged(val focusState: FocusState): UpdateTaskEvent()
    data class SubtitleFocusChanged(val focusState: FocusState): UpdateTaskEvent()
    data class DescriptionFocusChanged(val focusState: FocusState): UpdateTaskEvent()
    data class PriorityChanged(val priority: TaskPriority): UpdateTaskEvent()
    data class StatusChanged(val state: TaskStatus): UpdateTaskEvent()
    data class DateChanged(val date: Long): UpdateTaskEvent()
    object SaveTask: UpdateTaskEvent()
    object CancelTask: UpdateTaskEvent()
}