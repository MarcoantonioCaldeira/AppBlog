package com.blog.model.entity
import com.blog.com.blog.model.entity.Albums
import com.blog.com.blog.model.entity.Post
import com.blog.com.blog.model.entity.Task
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.persistence.OneToMany
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
@Table(name = "tb_user")
class User : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    var role : UserRole? = null

    var login : String? = null

    var name: String? = null

    var email: String? = null

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var passwordFild: String? = null

    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var confirmedPassword: String? = null

    @OneToMany(mappedBy = "user")
    var tasks : Set<Task> = hashSetOf()

    @OneToMany(mappedBy = "user")
    var posts : Set<Post> = hashSetOf()

    @OneToMany(mappedBy = "user")
    var albums : Set<Albums> = hashSetOf()

    constructor()

    constructor(role: UserRole?, login: String?, name: String?, email: String?, password: String?, confirmedPassword: String?) {
        this.role = role
        this.login = login
        this.name = name
        this.email = email
        this.passwordFild = password
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

    override fun getAuthorities(): Collection<GrantedAuthority?>? {
        if(this.role == UserRole.ADMIN) {
            return listOf(GrantedAuthority { "ROLE_ADMIN" })
        }
        return listOf(GrantedAuthority { "ROLE_USER" })
    }

    override fun getPassword(): String? = passwordFild

    override fun getUsername(): String? {
        return this.login
    }

}