package com.blog.repository
import com.blog.model.entity.Albums
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AlbumRepository : JpaRepository<Albums, Long> {
    fun findAlbumById(id: Long): Albums?
}