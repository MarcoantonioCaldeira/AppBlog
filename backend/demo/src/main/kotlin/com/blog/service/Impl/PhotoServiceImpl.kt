package com.blog.service.Impl
import com.blog.model.dto.PhotoDTO
import com.blog.repository.AlbumRepository
import com.blog.repository.PhotoRepository
import com.blog.service.PhotoService
import com.blog.model.entity.Photo
import com.blog.model.entity.User
import com.blog.service.exceptions.PhotoNotFoundException
import com.blog.service.mapper.EntityConverter
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class PhotoServiceImpl(
    private val photoRepository: PhotoRepository,
    private val converter: EntityConverter,
    private val albumRepository: AlbumRepository) : PhotoService {

    @Transactional
    override fun createPhoto(photoDTO: PhotoDTO): Photo {

        val album = photoDTO.albumId?.let { id ->
            albumRepository.findById(id)
                .orElseThrow { IllegalArgumentException("Álbum $id não encontrado") }
        }

        val user = albumRepository.findById(photoDTO.userId ?: 0).map { it.user }
            .orElseThrow { PhotoNotFoundException("Usuário não encontrado") }

        val photo = Photo(
            fileName = photoDTO.fileName,
            data = photoDTO.data,
            album = album,
            user = user
        )

        val savedPhoto = photoRepository.save(photo)
        return savedPhoto
    }

    override fun getPhotoById(id: Long): PhotoDTO? {
        val photo = photoRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Foto com id $id não encontrada") }
        return converter.parseObject(photo, PhotoDTO::class.java)
    }

    override fun getAllPhotos(): List<PhotoDTO> {
        return photoRepository.findAll().map { photos ->
            PhotoDTO(
                fileName = photos.fileName?: "",
                data = photos.data?: ByteArray(0),
                albumId = photos.album?.id?: 0,
                userId = photos.user?.id?: 0
            )
        }
    }

    @Transactional
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