package com.blog.demo.model.dto

data class UserDTO(
    var username: String? = null,
    var email: String? = null,
    var password: String? = null,
    var confirmedPassword: String? = null
)