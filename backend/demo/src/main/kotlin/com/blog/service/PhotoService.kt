package com.blog.com.blog.service
import com.blog.com.blog.model.dto.PhotoDTO

interface PhotoService {
    fun createPhoto(photoDTO: PhotoDTO): PhotoDTO
    fun getPhotoById(id: Long): PhotoDTO?
    fun getAllPhotos(): List<PhotoDTO>
    fun updatePhoto(id: Long, photoDTO: PhotoDTO): PhotoDTO
    fun deletePhoto(id: Long): String
}