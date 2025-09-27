package com.blog.com.blog.controller
import com.blog.com.blog.model.dto.PhotoDTO
import com.blog.com.blog.service.PhotoService
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/photos")
class PhotoController( private val photoService: PhotoService) {


    @PostMapping("/upload/{albumId}")
    fun uploadPhoto(
        @PathVariable albumId: Long,
        @RequestParam("file") file: MultipartFile
    ): ResponseEntity<PhotoDTO?> {
        val photoDTO = PhotoDTO(
            fileName = file.originalFilename ?: "sem_nome",
            data = file.bytes,
            albumId = albumId
        )

        val saved = photoService.createPhoto(photoDTO)
        return ResponseEntity.ok(saved)
    }

    @GetMapping("/{photoId}")
    fun downloadPhoto(@PathVariable photoId: Long): ResponseEntity<ByteArray> {
        val photo = photoService.getPhotoById(photoId)

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"${photo?.fileName}\"")
            .contentType(org.springframework.http.MediaType.IMAGE_JPEG)
            .body(photo?.data)
    }

    @GetMapping("/{id}")
    fun getPhotoById(@PathVariable id: Long): ResponseEntity<PhotoDTO> {
        val photo = photoService.getPhotoById(id)
        return ResponseEntity.ok(photo)
    }

    @GetMapping("/all")
    fun getAllPhotos(): ResponseEntity<List<PhotoDTO>> {
        val photos = photoService.getAllPhotos()
        return ResponseEntity.ok(photos)
    }

    @PutMapping("/{id}")
    fun updatePhoto(
        @PathVariable id: Long,
        @RequestBody photoDTO: PhotoDTO
    ): ResponseEntity<PhotoDTO> {
        val updated = photoService.updatePhoto(id, photoDTO)
        return ResponseEntity.ok(updated)
    }

    @DeleteMapping("/{id}")
    fun deletePhoto(@PathVariable id: Long): ResponseEntity<String> {
        val message = photoService.deletePhoto(id)
        return ResponseEntity.ok(message)
    }
}