package com.blog.model.dto

data class PostDTO(
    val id: Long? = null,
    val title: String? = null,
    val content: String? = null,
    val authorName: String? = null,
    val userId: Long? = null
)
