package com.blog.controller
import com.blog.model.dto.UserDTO
import com.blog.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @PostMapping("/create")
    fun createUser(@RequestBody userDTO: UserDTO): ResponseEntity<UserDTO?> {
        val createdUser = userService.createUser(userDTO)
        return ResponseEntity.ok(createdUser)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable id: Long): ResponseEntity<UserDTO> {
        val user = userService.getUserById(id)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/all")
    fun getAllUsers(): ResponseEntity<List<UserDTO>> {
        val users = userService.getAllUsers()
        return ResponseEntity.ok(users)
    }

    @PutMapping("update/{id}")
    fun updateUser(
        @PathVariable id: Long,
        @RequestBody userDTO: UserDTO
    ): ResponseEntity<UserDTO> {
        val updatedUser = userService.updateUser(id, userDTO)
        return ResponseEntity.ok(updatedUser)
    }

    @DeleteMapping("delete/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<String> {
        val message = userService.deleteUser(id)
        return ResponseEntity.ok(message)
    }

}