package com.singhdevs.snaptask.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    val title: String,
    val subtitle: String,
    val description: String,
    val priority: TaskPriority,
    val date: Long,
    val status: TaskStatus,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)