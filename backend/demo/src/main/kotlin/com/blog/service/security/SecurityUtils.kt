package com.blog.com.blog.service.security

import com.blog.model.entity.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class SecurityUtils {

    fun getCurrentUserId(): Long {
        val authentication = SecurityContextHolder.getContext().authentication
        val userDetails = authentication.principal as User
        return userDetails.id ?: throw RuntimeException("Usuário não autenticado")
    }
}