package com.blog.com.blog.repository

import com.blog.com.blog.model.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findCommentById(postId: Long): Comment
}