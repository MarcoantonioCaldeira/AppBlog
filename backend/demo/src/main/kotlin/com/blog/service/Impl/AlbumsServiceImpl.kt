package com.blog.com.blog.service.Impl

import com.blog.com.blog.model.dto.AlbumsDTO
import com.blog.com.blog.model.entity.Albums
import com.blog.com.blog.repository.AlbumRepository
import com.blog.com.blog.service.AlbumsService
import com.blog.repository.UserRepository
import com.blog.service.mapper.EntityConverter
import org.springframework.stereotype.Service

@Service
class AlbumsServiceImpl(
    private val albumRepository: AlbumRepository,
    private val converter: EntityConverter,
    private val userRepository: UserRepository) : AlbumsService {

    override fun createAlbum(albumsDTO: AlbumsDTO): Albums {

        val user = userRepository.findById(albumsDTO.userId)
            .orElseThrow { IllegalArgumentException("Usuário com id ${albumsDTO.userId} não encontrado") }

        val albums = Albums(
            title = albumsDTO.title,
            user = user
        )

        return albumRepository.save(albums)
    }

    override fun getAlbumsById(id: Long): Albums? {
        return albumRepository.findAlbumById(id);
    }

    override fun getAllAlbums(): List<AlbumsDTO> {
        return albumRepository.findAll().map { albums ->
            AlbumsDTO(
                title = albums.title ?: "",
                userId = albums.user?.id
            )
        }
    }

    override fun updateAlbum(
        id: Long,
        albumsDTO: AlbumsDTO
    ): AlbumsDTO {
        val existinAlbums = albumRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Album com id $id não encontrado") }

        existinAlbums.apply    {
            title = albumsDTO.title ?: this.title
        }

        val updatedAlbums = albumRepository.save(existinAlbums)
        return converter.parseObject(updatedAlbums, AlbumsDTO::class.java)
    }

    override fun deleteAlbum(id: Long): String {
        val existinAlbums = albumRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Album com id $id não encontrado") }

        albumRepository.delete(existinAlbums)
        return "Album com id $id deletado com sucesso"
    }
}