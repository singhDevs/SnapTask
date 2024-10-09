package com.singhdevs.snaptask.data.dataSource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.singhdevs.snaptask.domain.model.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Query("SELECT * from task")
    fun getTasks(): Flow<List<Task>>

    @Query("SELECT * from task WHERE id = :id")
    suspend fun getTaskById(id: Int): Task?

    @Query("SELECT * from task WHERE priority = :priority")
    fun getTasksByPriority(priority: Int): Flow<List<Task>>

    @Query("SELECT * from task WHERE status = :status")
    fun getTasksByStatus(status: Int): Flow<List<Task>>

    @Query("SELECT * from task WHERE date = :date")
    fun getTasksByDate(date: Long): Flow<List<Task>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

}