package com.blog.service.Impl

import com.blog.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AuthorizationServiceImpl(private val userRepository : UserRepository) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByLogin(username)
            ?: throw RuntimeException("Usuario não encontrado: $username")
    }
}