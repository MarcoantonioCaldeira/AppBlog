package com.blog.com.blog.service
import com.blog.com.blog.model.dto.AlbumsDTO

interface AlbumsService {
    fun createAlbum(albumsDTO: AlbumsDTO): AlbumsDTO
    fun getAlbumsById(id: Long): AlbumsDTO?
    fun getAllAlbums(): List<AlbumsDTO>
    fun updateAlbum(id: Long, albumsDTO: AlbumsDTO): AlbumsDTO
    fun deleteAlbum(id: Long): String
}