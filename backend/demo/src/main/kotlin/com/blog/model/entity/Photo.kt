package com.blog.model.entity
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.Lob
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "tb_photo")
class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    var fileName: String? = null

    @Lob
    @JsonIgnore
    var data: ByteArray? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    var album: Albums? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @ManyToOne
    @JoinColumn(name = "user_id")
    var user : User? = null

    constructor()

    constructor(fileName: String?, data: ByteArray?, album: Albums?, user: User?) {
        this.fileName = fileName
        this.data = data
        this.album = album
        this.user = user
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Photo

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }
}