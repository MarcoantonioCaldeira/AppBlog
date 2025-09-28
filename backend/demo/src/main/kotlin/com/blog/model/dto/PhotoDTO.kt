package com.blog.model.dto

data class PhotoDTO(
    val fileName: String? = null,
    val data: ByteArray? = null,
    val albumId: Long? = null,
    val userId: Long? = null
)
