package com.blog.com.blog.service
import com.blog.com.blog.model.dto.CommentDTO

interface CommentService {
    fun createComment(commentDTO: CommentDTO): CommentDTO
    fun getCommentById(id: Long): CommentDTO?
    fun getAllComments(): List<CommentDTO>
    fun updateComment(id: Long, commentDTO: CommentDTO): CommentDTO
    fun deleteComment(id: Long): String
}