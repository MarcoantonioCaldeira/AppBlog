package com.blog.com.blog.service.Impl
import com.blog.com.blog.model.dto.PostDTO
import com.blog.com.blog.model.entity.Post
import com.blog.com.blog.repository.PostRepository
import com.blog.com.blog.service.PostService
import com.blog.repository.UserRepository
import com.blog.service.exceptions.PostNotFoundException
import com.blog.service.mapper.EntityConverter
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PostServiceImpl(
    private val postRepository: PostRepository,
    private val converter: EntityConverter,
    private val userRepository : UserRepository
) : PostService {

    @Transactional
    override fun createPost(postDTO: PostDTO): PostDTO {

        val user = userRepository.findById(postDTO.userId)
            .orElseThrow { PostNotFoundException("Usuário com id ${postDTO.userId} não encontrado") }

        val post = Post(
            title = postDTO.title,
            content = postDTO.content,
            author = postDTO.authorName,
            user = user
        )

        val savedPost = postRepository.save(post)
        return converter.parseObject(savedPost, PostDTO::class.java)
    }

    override fun getPostById(id: Long): PostDTO? {
        val post = postRepository.findById(id)
            .orElseThrow { PostNotFoundException("Post com id $id não encontrado") }
        return converter.parseObject(post, PostDTO::class.java)
    }

    override fun getAllPosts(): List<PostDTO> {
        return postRepository.findAll().map { post ->
            PostDTO(
                id = post.id,
                title = post.title ?: "",
                content = post.content ?: "",
                authorName = post.author ?: "",
                userId = post.user?.id
            )
        }
    }

    @Transactional
    override fun updatePost(
        id: Long,
        postDTO: PostDTO
    ): PostDTO {
        val existinPost = postRepository.findById(id)
            .orElseThrow { PostNotFoundException("Post com id $id não encontrado") }

        existinPost.apply    {
            title = postDTO.title ?: this.title
            content = postDTO.content ?: this.content
            author = postDTO.authorName ?: this.author
        }

        val updatedPost = postRepository.save(existinPost)
        return converter.parseObject(updatedPost, PostDTO::class.java)
    }

    @Transactional
    override fun deletePost(id: Long): String {
        val existinPost = postRepository.findById(id)
            .orElseThrow { PostNotFoundException("Post com id $id não encontrado") }

        postRepository.delete(existinPost)
        return "Post com id $id deletado com sucesso"
    }
}