package com.blog.com.blog.service.Impl

import com.blog.com.blog.model.dto.PhotoDTO
import com.blog.com.blog.model.entity.Photo
import com.blog.com.blog.repository.AlbumRepository
import com.blog.com.blog.repository.PhotoRepository
import com.blog.com.blog.service.PhotoService
import com.blog.service.mapper.EntityConverter
import org.springframework.stereotype.Service

@Service
class PhotoServiceImpl(
    private val photoRepository: PhotoRepository,
    private val converter: EntityConverter,
    private val albumRepository: AlbumRepository) : PhotoService {

    override fun createPhoto(photoDTO: PhotoDTO): Photo {

        val album = albumRepository.findById(photoDTO.albumId)
            .orElseThrow { IllegalArgumentException("Album não encontrado") }

        val photo = Photo(
            fileName = photoDTO.fileName,
            data = photoDTO.data,
            album = album
        )

        return photoRepository.save(photo)
        //return converter.parseObject(savedPhoto, PhotoDTO::class.java)
    }

    override fun getPhotoById(id: Long): Photo? {
        return photoRepository.findPhotoById(id);
    }

    override fun getAllPhotos(): List<PhotoDTO> {
        return photoRepository.findAll().map { photos ->
            PhotoDTO(
                fileName = photos.fileName?: "",
                data = photos.data?: ByteArray(0),
                albumId = photos.album?.id?: 0
            )
        }
    }

    override fun updatePhoto(
        id: Long,
        photoDTO: PhotoDTO
    ): PhotoDTO {
        val existinPhotos = photoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Foto não encontrada") }

        existinPhotos.apply    {
            fileName = photoDTO.fileName ?: this.fileName
            data = photoDTO.data ?: this.data

        }

        val updatedPhotos = photoRepository.save(existinPhotos)
        return converter.parseObject(updatedPhotos, PhotoDTO::class.java)
    }

    override fun deletePhoto(id: Long): String {
        val exitinPhoto = photoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Foto não encontrada") }

        photoRepository.delete(exitinPhoto)
        return "Foto deletada com sucesso"
    }
}