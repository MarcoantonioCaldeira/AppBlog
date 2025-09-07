package com.blog.repository
import com.blog.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.id = :id")
    fun getUserById(id: Long): User?

    fun findByEmail(email: String): User?
    fun findByLogin(login: String): User?
}