package com.blog.com.blog.model.dto

import com.blog.model.entity.User

data class TaskDTO(
    val id: Long? = null,
    val title: String? = null,
    val description: String? = null,
    val userId: Long? = null
)
