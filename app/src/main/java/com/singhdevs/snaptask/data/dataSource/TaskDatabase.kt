package com.singhdevs.snaptask.data.dataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.singhdevs.snaptask.domain.model.Task

@Database(
    entities = [Task::class],
    version = 1,
)
abstract class TaskDatabase: RoomDatabase() {
    abstract val taskDao: TaskDAO

    companion object {
        const val DATABASE_NAME = "tasks_db"
    }
}