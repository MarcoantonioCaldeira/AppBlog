package com.blog.com.blog.repository

import com.blog.com.blog.model.entity.Albums
import org.springframework.data.jpa.repository.JpaRepository

interface AlbumRepository : JpaRepository<Albums, Long> {
    fun findAlbumById(id: Long): Albums?
}