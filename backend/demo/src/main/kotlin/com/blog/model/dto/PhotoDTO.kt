package com.blog.com.blog.model.dto

data class PhotoDTO(
    val fileName: String,
    val data: ByteArray,
    val albumId: Long
)
