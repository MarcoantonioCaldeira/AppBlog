package com.blog.model.dto

data class AlbumsDTO(
    val id: Long? = null,
    val title: String? = null,
    val userId: Long? = null,
    val photos: Set<PhotoDTO> = emptySet()
)