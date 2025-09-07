package com.blog.com.blog.controller
import com.blog.com.blog.model.dto.TaskDTO
import com.blog.com.blog.model.entity.Task
import com.blog.com.blog.service.TaskService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("api/tasks")
class TaskController(private val taskService: TaskService) {

    @PostMapping("/create")
    fun createTask(@RequestBody taskDTO: TaskDTO): ResponseEntity<Task?> {
        val createdTask = taskService.createTask(taskDTO)
        return ResponseEntity.ok(createdTask)
    }

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<Task> {
        val task = taskService.getTaskById(id)
        return ResponseEntity.ok(task)
    }

    @GetMapping("/all")
    fun getAllTasks(): ResponseEntity<List<TaskDTO>> {
        val tasks = taskService.getAllTasks()
        return ResponseEntity.ok(tasks)
    }

    @PutMapping("update/{id}")
    fun updateTask(
        @PathVariable id: Long,
        @RequestBody taskDTO: TaskDTO
    ): ResponseEntity<TaskDTO> {
        val updateTask = taskService.updateTask(id, taskDTO)
        return ResponseEntity.ok(updateTask)
    }

    @DeleteMapping("delete/{id}")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<String> {
        val message = taskService.deleteTask(id)
        return ResponseEntity.ok(message)
    }
}