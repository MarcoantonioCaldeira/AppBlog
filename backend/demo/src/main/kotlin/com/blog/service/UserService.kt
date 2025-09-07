package com.blog.service
import com.blog.model.entity.User
import com.blog.model.dto.UserDTO


interface UserService {
    fun createUser(userDTO: UserDTO): User
    fun getUserById(id: Long): User?
    fun getAllUsers(): List<UserDTO>
    fun updateUser(id: Long, userDTO: UserDTO): UserDTO
    fun deleteUser(id: Long): String
}