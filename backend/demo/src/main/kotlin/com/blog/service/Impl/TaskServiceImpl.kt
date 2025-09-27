package com.blog.com.blog.service.Impl
import com.blog.com.blog.model.dto.TaskDTO
import com.blog.com.blog.model.entity.Task
import com.blog.com.blog.repository.TaskRepository
import com.blog.com.blog.service.TaskService
import com.blog.com.blog.service.exceptions.TaskNotFoundException
import com.blog.repository.UserRepository
import com.blog.service.exceptions.UserNotFoundException
import com.blog.service.mapper.EntityConverter
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val converter: EntityConverter,
    private val userRepository : UserRepository
) : TaskService{
    @Transactional
    override fun createTask(taskDTO: TaskDTO): TaskDTO {

        if(taskRepository.findByTitle(taskDTO.title ?: "") != null) {
            throw IllegalArgumentException("Essa tarefa já existe")
        }

        val user = userRepository.findById(taskDTO.userId)
            .orElseThrow { UserNotFoundException("Usuário com id ${taskDTO.userId} não encontrado") }

        val task = Task(
            title = taskDTO.title,
            description = taskDTO.description,
            user = user
        )

        val savedTask = taskRepository.save(task)
        return converter.parseObject(savedTask, TaskDTO::class.java)
    }

    override fun getTaskById(id: Long): TaskDTO? {
        val task = taskRepository.findById(id)
            .orElseThrow { TaskNotFoundException("Tarefa com id $id não encontrada") }
        return converter.parseObject(task, TaskDTO::class.java)
    }

    override fun getAllTasks(): List<TaskDTO> {
        return taskRepository.findAll().map { task ->
            TaskDTO(
                title = task.title ?: "",
                description = task.description ?: ""
            )
        }
    }

    @Transactional
    override fun updateTask(
        id: Long,
        taskDTO: TaskDTO
    ): TaskDTO {
        val existinTask = taskRepository.findById(id)
            .orElseThrow { TaskNotFoundException("Tarefa com id $id não encontrada") }

        existinTask.apply    {
            title = taskDTO.title ?: this.title
            description = taskDTO.description ?: this.description
        }

        val updatedTask = taskRepository.save(existinTask)
        return converter.parseObject(updatedTask, TaskDTO::class.java)
    }

    @Transactional
    override fun deleteTask(id: Long): String {
        val existinTask = taskRepository.findTaskById(id)
            ?: throw TaskNotFoundException("Tarefa com id $id não encontrada")

        taskRepository.delete(existinTask)
        return "Tarefa com id $id deletada com sucesso"
    }

}