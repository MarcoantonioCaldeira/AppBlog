package com.blog.com.blog.service
import com.blog.com.blog.model.dto.PostDTO

interface PostService {
    fun createPost(postDTO: PostDTO): PostDTO
    fun getPostById(id: Long): PostDTO?
    fun getAllPosts(): List<PostDTO>
    fun updatePost(id: Long, postDTO: PostDTO): PostDTO
    fun deletePost(id: Long): String
}