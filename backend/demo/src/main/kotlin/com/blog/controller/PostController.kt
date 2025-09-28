package com.blog.controller
import com.blog.model.dto.PostDTO
import com.blog.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("api/posts")
class PostController(private val postService : PostService) {

    @PostMapping("/create")
    fun createPost(@RequestBody postDTO: PostDTO): ResponseEntity<PostDTO?> {
        val createdPost = postService.createPost(postDTO)
        return ResponseEntity.ok(createdPost)
    }

    @GetMapping("/{id}")
    fun getPostById(@PathVariable id: Long): ResponseEntity<PostDTO> {
        val post = postService.getPostById(id)
        return ResponseEntity.ok(post)
    }

    @GetMapping("/all")
    fun getAllPosts(): ResponseEntity<List<PostDTO>> {
        val posts = postService.getAllPosts()
        return ResponseEntity.ok(posts)
    }

    @PutMapping("update/{id}")
    fun updatePost(
        @PathVariable id: Long,
        @RequestBody postDTO: PostDTO
    ): ResponseEntity<PostDTO> {
        val updatePost = postService.updatePost(id, postDTO)
        return ResponseEntity.ok(updatePost)
    }

    @DeleteMapping("delete/{id}")
    fun deletePost(@PathVariable id: Long): ResponseEntity<String> {
        val message = postService.deletePost(id)
        return ResponseEntity.ok(message)
    }

}