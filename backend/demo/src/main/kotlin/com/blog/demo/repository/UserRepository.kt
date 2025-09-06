package com.blog.demo.repository

import com.blog.demo.model.User
import com.blog.demo.model.dto.UserDTO
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun getUserById(id: Long): UserDTO
    fun findByEmail(email: String): User?
}