package com.singhdevs.snaptask.domain.model

import androidx.compose.ui.graphics.Color

enum class TaskStatus(val value: Int, val color: Color) {
    NOT_STARTED(0, Color.Gray),
    IN_PROGRESS(1, Color.Yellow),
    COMPLETED(2, Color.Green)
}