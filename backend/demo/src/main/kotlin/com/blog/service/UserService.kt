package com.blog.service
import com.blog.model.dto.UserDTO


interface UserService {
    fun createUser(userDTO: UserDTO): UserDTO
    fun getUserById(id: Long): UserDTO?
    fun getAllUsers(): List<UserDTO>
    fun updateUser(id: Long, userDTO: UserDTO): UserDTO
    fun deleteUser(id: Long): String
}