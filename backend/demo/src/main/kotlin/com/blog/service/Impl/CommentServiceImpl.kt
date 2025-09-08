package com.blog.com.blog.service.Impl
import com.blog.com.blog.model.dto.CommentDTO
import com.blog.com.blog.model.entity.Comment
import com.blog.com.blog.repository.CommentRepository
import com.blog.com.blog.repository.PostRepository
import com.blog.com.blog.service.CommentService
import com.blog.service.mapper.EntityConverter
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val converter: EntityConverter,
    private val postRepository: PostRepository
) : CommentService {

    override fun createComment(commentDTO: CommentDTO): Comment {

        val post = postRepository.findById(commentDTO.postId)
            .orElseThrow { IllegalArgumentException("Post com id ${commentDTO.postId} não encontrado") }

        val comment = Comment(
            text = commentDTO.text,
            post = post
        )

        return commentRepository.save(comment)
    }

    override fun getCommentById(id: Long): Comment? {
        return commentRepository.findCommentById(id);
    }

    override fun getAllComments(): List<CommentDTO> {
        return commentRepository.findAll().map { post ->
            CommentDTO(
                text = post.text ?: "",
                postId = post.post?.id
            )
        }
    }


    override fun updateComment(
        id: Long,
        commentDTO: CommentDTO
    ): CommentDTO {
        val existinComment = commentRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Comentario com id $id não encontrado") }

        existinComment.apply    {
            text = commentDTO.text ?: this.text
        }

        val updatedComment = commentRepository.save(existinComment)
        return converter.parseObject(updatedComment, CommentDTO::class.java)
    }

    override fun deleteComment(id: Long): String {
        val existinComment = commentRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Comentario com id $id não encontrado") }

        commentRepository.delete(existinComment)
        return "Comentario com id $id deletado com sucesso"
    }
}