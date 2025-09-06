package com.blog.demo.model.dto

data class UserDTO(
    val id: Long? = null,
    val username: String,
    val email: String,
    val password: String,
    val confirmedPassword: String
)