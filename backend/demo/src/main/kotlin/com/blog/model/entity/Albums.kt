package com.blog.model.entity
import com.blog.model.dto.PhotoDTO
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.CascadeType
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "tb_albums")
class Albums {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    var title: String? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user : User? = null

    @OneToMany(mappedBy = "album", cascade = [CascadeType.ALL], orphanRemoval = true)
    var photos: MutableSet<Photo> = mutableSetOf()

    constructor()

    constructor(title: String?, user: User?) {
        this.title = title
        this.user = user
    }

    fun addPhoto(photo: Photo) {
        photos.add(photo)
        photo.album = this
    }

    fun removePhoto(photo: Photo) {
        photos.remove(photo)
        photo.album = null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Albums

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}