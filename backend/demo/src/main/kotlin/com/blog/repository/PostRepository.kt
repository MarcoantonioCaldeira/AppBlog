package com.blog.repository

import com.blog.model.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PostRepository : JpaRepository<Post, Long> {
    fun findPostById(id: Long): Post?
}