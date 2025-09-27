package com.blog.model.dto
import com.blog.model.entity.UserRole


data class UserDTO(
    var name: String? = null,
    var login : String? = null,
    var role: UserRole? = null,
    var email: String? = null,
    var passwordField: String? = null,
    var confirmedPassword: String? = null
)