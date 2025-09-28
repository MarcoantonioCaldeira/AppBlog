package com.blog.com.blog.controller
import com.blog.model.dto.CommentDTO
import com.blog.service.CommentService
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
@RequestMapping("api/comments")
class CommentController(private val commentService: CommentService) {

    @PostMapping("/create")
    fun createComment(@RequestBody commentDTO: CommentDTO): ResponseEntity<CommentDTO?> {
        val createdComment = commentService.createComment(commentDTO)
        return ResponseEntity.ok(createdComment)
    }

    @GetMapping("/{id}")
    fun getCommentById(@PathVariable id: Long): ResponseEntity<CommentDTO> {
        val comment = commentService.getCommentById(id)
        return ResponseEntity.ok(comment)
    }

    @GetMapping("/all")
    fun getAllComment(): ResponseEntity<List<CommentDTO>> {
        val comments = commentService.getAllComments()
        return ResponseEntity.ok(comments)
    }

    @PutMapping("update/{id}")
    fun updateComment(
        @PathVariable id: Long,
        @RequestBody commentDTO: CommentDTO
    ): ResponseEntity<CommentDTO> {
        val updateComment = commentService.updateComment(id, commentDTO)
        return ResponseEntity.ok(updateComment)
    }

    @DeleteMapping("delete/{id}")
    fun deleteComment(@PathVariable id: Long): ResponseEntity<String> {
        val message = commentService.deleteComment(id)
        return ResponseEntity.ok(message)
    }

}