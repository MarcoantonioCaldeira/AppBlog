package com.blog.com.blog.service.Impl
import com.blog.com.blog.model.dto.CommentDTO
import com.blog.com.blog.model.entity.Comment
import com.blog.com.blog.repository.CommentRepository
import com.blog.com.blog.repository.PostRepository
import com.blog.com.blog.service.CommentService
import com.blog.service.exceptions.CommentNotFoundException
import com.blog.service.mapper.EntityConverter
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val converter: EntityConverter,
    private val postRepository: PostRepository
) : CommentService {

    @Transactional
    override fun createComment(commentDTO: CommentDTO): CommentDTO {

        val post = postRepository.findById(commentDTO.postId)
            .orElseThrow { CommentNotFoundException("Post com id ${commentDTO.postId} não encontrado") }

        val comment = Comment(
            text = commentDTO.text,
            post = post
        )

        val commentsaved = commentRepository.save(comment)
        return converter.parseObject(commentsaved, CommentDTO::class.java)
    }

    override fun getCommentById(id: Long): CommentDTO? {
        val comment = commentRepository.findById(id)
            .orElseThrow { CommentNotFoundException("Comentario com id $id não encontrado") }

        return converter.parseObject(comment, CommentDTO::class.java)
    }

    override fun getAllComments(): List<CommentDTO> {
        return commentRepository.findAll().map { post ->
            CommentDTO(
                text = post.text ?: "",
                postId = post.post?.id
            )
        }
    }

    @Transactional
    override fun updateComment(
        id: Long,
        commentDTO: CommentDTO
    ): CommentDTO {
        val existinComment = commentRepository.findById(id)
            .orElseThrow { CommentNotFoundException("Comentario com id $id não encontrado") }

        existinComment.apply    {
            text = commentDTO.text ?: this.text
        }

        val updatedComment = commentRepository.save(existinComment)
        return converter.parseObject(updatedComment, CommentDTO::class.java)
    }

    @Transactional
    override fun deleteComment(id: Long): String {
        val existinComment = commentRepository.findById(id)
            .orElseThrow { CommentNotFoundException("Comentario com id $id não encontrado") }

        commentRepository.delete(existinComment)
        return "Comentario com id $id deletado com sucesso"
    }
}