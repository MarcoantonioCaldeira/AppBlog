package com.blog.repository

import com.blog.model.entity.Photo
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PhotoRepository : JpaRepository<Photo, Long>{
    fun findAllByAlbumId(albumId: Long): List<Photo>

    fun findPhotoById(id: Long): Photo?
}