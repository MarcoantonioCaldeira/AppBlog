package com.blog.controller
import com.blog.model.dto.AuthenticationDTO
import com.blog.model.dto.LoginDTO
import com.blog.model.entity.User
import com.blog.service.security.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class AuthController(private val autenticationManager: AuthenticationManager, private val tokenService: TokenService) {

    @PostMapping("/login")
    fun login(@RequestBody authenticationDTO: AuthenticationDTO): ResponseEntity<LoginDTO> {
        val authenticationToken = UsernamePasswordAuthenticationToken(
            authenticationDTO.login,
            authenticationDTO.passwordFild
        )

        val authentication = autenticationManager.authenticate(authenticationToken)
        val token = tokenService.generateToken(authentication.principal as User)

        return ResponseEntity.ok(LoginDTO(token))
    }
}