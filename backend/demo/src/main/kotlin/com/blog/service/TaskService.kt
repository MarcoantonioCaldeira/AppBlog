package com.blog.com.blog.service
import com.blog.com.blog.model.dto.TaskDTO
import com.blog.com.blog.model.entity.Task


interface TaskService {
    fun createTask(taskDTO: TaskDTO): Task
    fun getTaskById(id: Long): Task?
    fun getAllTasks(): List<TaskDTO>
    fun updateTask(id: Long, taskDTO: TaskDTO): TaskDTO
    fun deleteTask(id: Long): String
}