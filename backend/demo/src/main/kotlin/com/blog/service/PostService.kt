package com.blog.com.blog.service
import com.blog.com.blog.model.dto.PostDTO
import com.blog.com.blog.model.entity.Post

interface PostService {
    fun createPost(postDTO: PostDTO): Post
    fun getPostById(id: Long): Post?
    fun getAllPosts(): List<PostDTO>
    fun updatePost(id: Long, postDTO: PostDTO): PostDTO
    fun deletePost(id: Long): String
}