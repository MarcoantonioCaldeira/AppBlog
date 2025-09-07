package com.blog.com.blog.repository

import com.blog.com.blog.model.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    fun findTaskById(id: Long): Task?
    fun findByTitle(title: String): Task?

}