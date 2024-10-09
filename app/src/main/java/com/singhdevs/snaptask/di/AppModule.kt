package com.singhdevs.snaptask.di

import android.app.Application
import androidx.room.Room
import com.singhdevs.snaptask.data.dataSource.TaskDatabase
import com.singhdevs.snaptask.data.repository.TaskRepositoryImpl
import com.singhdevs.snaptask.domain.repository.TaskRepository
import com.singhdevs.snaptask.domain.useCases.AddTask
import com.singhdevs.snaptask.domain.useCases.DeleteTask
import com.singhdevs.snaptask.domain.useCases.GetTaskById
import com.singhdevs.snaptask.domain.useCases.GetTasks
import com.singhdevs.snaptask.domain.useCases.GetTasksByDate
import com.singhdevs.snaptask.domain.useCases.GetTasksByPriority
import com.singhdevs.snaptask.domain.useCases.GetTasksByStatus
import com.singhdevs.snaptask.domain.useCases.TaskUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideTaskDatabase(application: Application): TaskDatabase{
        return Room.databaseBuilder(
            application,
            TaskDatabase::class.java,
            TaskDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTaskRepository(db: TaskDatabase): TaskRepository {
        return TaskRepositoryImpl(db.taskDao)
    }

    @Provides
    @Singleton
    fun provideTaskUseCases(repository: TaskRepository): TaskUseCases{
        return TaskUseCases(
            getTasks = GetTasks(repository),
            getTasksByPriority = GetTasksByPriority(repository),
            getTasksByStatus = GetTasksByStatus(repository),
            getTasksByDate = GetTasksByDate(repository),
            getTaskById = GetTaskById(repository),
            addTask = AddTask(repository),
            deleteTask = DeleteTask(repository)
        )
    }
}