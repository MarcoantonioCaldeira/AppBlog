package com.blog.com.blog.service

import com.blog.com.blog.model.dto.AlbumsDTO
import com.blog.com.blog.model.entity.Albums

interface AlbumsService {
    fun createAlbum(albumsDTO: AlbumsDTO): Albums
    fun getAlbumsById(id: Long): Albums?
    fun getAllAlbums(): List<AlbumsDTO>
    fun updateAlbum(id: Long, albumsDTO: AlbumsDTO): AlbumsDTO
    fun deleteAlbum(id: Long): String
}