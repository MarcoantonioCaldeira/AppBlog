package com.blog.com.blog.model.entity

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
    var data: ByteArray? = null


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "album_id")
    var album: Albums ? = null

    constructor()

    constructor(fileName: String?, data: ByteArray?, album: Albums?) {
        this.fileName = fileName
        this.data = data
        this.album = album
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