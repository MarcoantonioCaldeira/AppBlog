package com.blog.com.blog.service
import com.blog.com.blog.model.dto.PhotoDTO
import com.blog.com.blog.model.entity.Photo

interface PhotoService {
    fun createPhoto(photoDTO: PhotoDTO): Photo
    fun getPhotoById(id: Long): Photo?
    fun getAllPhotos(): List<PhotoDTO>
    fun updatePhoto(id: Long, photoDTO: PhotoDTO): PhotoDTO
    fun deletePhoto(id: Long): String
}