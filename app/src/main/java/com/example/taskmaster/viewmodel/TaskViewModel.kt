package com.example.taskmaster.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.taskmaster.data.Task
import java.time.LocalDate
import java.time.LocalTime

class TaskViewModel : ViewModel() {
    // Estado observable de las tareas
    private var _tasks by mutableStateOf<List<Task>>(emptyList())
    val tasks: List<Task> get() = _tasks

    // Contador para IDs únicos
    private var nextId = 1


    fun addTask(
        title: String,
        description: String,
        dueDate: LocalDate?,
        dueTime: LocalTime?
    ) {
        _tasks = _tasks + Task(
            id = nextId++,
            title = title,
            description = description,
            dueDate = dueDate,
            dueTime = dueTime,
            isCompleted = false
        )
    }

    /**
     * Cambia el estado de completado de una tarea
     * @param taskId ID de la tarea a modificar
     */
    fun toggleTaskCompletion(taskId: Int) {
        _tasks = _tasks.map { task ->
            if (task.id == taskId) {
                task.copy(isCompleted = !task.isCompleted)
            } else {
                task
            }
        }
    }

    /**
     * Elimina una tarea de la lista
     * @param taskId ID de la tarea a eliminar
     */
    fun deleteTask(taskId: Int) {
        _tasks = _tasks.filterNot { it.id == taskId }
    }

    /**
     * Actualiza una tarea existente
     * @param updatedTask Tarea con los datos actualizados
     */
    fun updateTask(updatedTask: Task) {
        _tasks = _tasks.map { task ->
            if (task.id == updatedTask.id) updatedTask else task
        }
    }
}