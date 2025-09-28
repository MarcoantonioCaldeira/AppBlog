package com.blog.repository
import com.blog.model.entity.Comment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CommentRepository : JpaRepository<Comment, Long> {
    fun findCommentById(postId: Long): Comment
}