package com.blog.demo.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.*

@Entity
@Table(name = "tb_user")
class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var username: String? = null

    var email: String? = null

    var password: String? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var confirmedPassword: String? = null

    constructor()

    constructor(username: String?, email: String?, password: String?, confirmedPassword: String?) {
        this.username = username
        this.email = email
        this.password = password
        this.confirmedPassword = confirmedPassword
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        return id == other.id
    }

    override fun hashCode(): Int {
        return id?.hashCode() ?: 0
    }

}