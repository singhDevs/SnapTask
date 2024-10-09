package com.singhdevs.snaptask.domain.useCases

import android.util.Log
import android.widget.Toast
import com.singhdevs.snaptask.domain.model.Errors.FieldsNotFilledException
import com.singhdevs.snaptask.domain.model.Task
import com.singhdevs.snaptask.domain.repository.TaskRepository
import kotlin.jvm.Throws

class AddTask(
    private val repository: TaskRepository
) {

    @Throws(FieldsNotFilledException::class)
    suspend operator fun invoke(task: Task){
        if(task.title.isBlank() || task.subtitle.isBlank() || task.description.isBlank()){
            throw FieldsNotFilledException("Fields cannot be empty.")
        }
        Log.d("SnapTask", "Saving Task...")
        repository.addTask(task)
    }
}