package com.blog.demo.service
import com.blog.demo.model.User
import com.blog.demo.model.dto.UserDTO


interface UserService {
    fun createUser(userDTO: UserDTO): User
    fun getUserById(id: Long): UserDTO
    fun getAllUsers(): List<UserDTO>
    fun updateUser(id: Long, userDTO: UserDTO): UserDTO
    fun deleteUser(id: Long): String
}