package com.blog.com.blog.service
import com.blog.com.blog.model.dto.TaskDTO


interface TaskService {
    fun createTask(taskDTO: TaskDTO): TaskDTO
    fun getTaskById(id: Long): TaskDTO?
    fun getAllTasks(): List<TaskDTO>
    fun updateTask(id: Long, taskDTO: TaskDTO): TaskDTO
    fun deleteTask(id: Long): String
}