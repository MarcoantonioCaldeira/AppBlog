package com.blog.com.blog.controller

import com.blog.com.blog.model.dto.AlbumsDTO
import com.blog.com.blog.model.entity.Albums
import com.blog.com.blog.service.Impl.AlbumsServiceImpl
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
@RequestMapping("api/albums")
class AlbumController(private val albumService : AlbumsServiceImpl) {

    @PostMapping("/create")
    fun createAlbum(@RequestBody albumsDTO: AlbumsDTO): ResponseEntity<Albums?> {
        val createdAlbum = albumService.createAlbum(albumsDTO)
        return ResponseEntity.ok(createdAlbum)
    }

    @GetMapping("/{id}")
    fun getAlbumById(@PathVariable id: Long): ResponseEntity<Albums> {
        val albums = albumService.getAlbumsById(id)
        return ResponseEntity.ok(albums)
    }

    @GetMapping("/all")
    fun getAllAlbums(): ResponseEntity<List<AlbumsDTO>> {
        val albums = albumService.getAllAlbums()
        return ResponseEntity.ok(albums)
    }

    @PutMapping("update/{id}")
    fun updatePost(
        @PathVariable id: Long,
        @RequestBody albumsDTO: AlbumsDTO
    ): ResponseEntity<AlbumsDTO> {
        val updateAlbums = albumService.updateAlbum(id, albumsDTO)
        return ResponseEntity.ok(updateAlbums)
    }

    @DeleteMapping("delete/{id}")
    fun deleteAlbum(@PathVariable id: Long): ResponseEntity<String> {
        val message = albumService.deleteAlbum(id)
        return ResponseEntity.ok(message)
    }

}