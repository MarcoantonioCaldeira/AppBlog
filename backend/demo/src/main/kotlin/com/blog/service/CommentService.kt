package com.blog.com.blog.service
import com.blog.com.blog.model.dto.CommentDTO
import com.blog.com.blog.model.entity.Comment

interface CommentService {
    fun createComment(commentDTO: CommentDTO): Comment
    fun getCommentById(id: Long): Comment?
    fun getAllComments(): List<CommentDTO>
    fun updateComment(id: Long, commentDTO: CommentDTO): CommentDTO
    fun deleteComment(id: Long): String
}