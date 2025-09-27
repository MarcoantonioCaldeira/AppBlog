package com.blog.service.impl
import com.blog.service.exceptions.EmailAlreadyExistsException
import com.blog.service.exceptions.PasswordMismatchException
import com.blog.service.exceptions.UserNotFoundException
import com.blog.service.mapper.EntityConverter
import com.blog.model.entity.User
import com.blog.model.dto.UserDTO
import com.blog.repository.UserRepository
import com.blog.service.UserService
import jakarta.transaction.Transactional
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val converter: EntityConverter
) : UserService {

    private val passwordEncoder = BCryptPasswordEncoder()

    @Transactional
    override fun createUser(userDTO: UserDTO): UserDTO {
        userRepository.findByEmail(userDTO.email!!)
            ?.let { throw EmailAlreadyExistsException("E-mail já cadastrado: ${userDTO.email}") }

        if (userDTO.passwordField != userDTO.confirmedPassword) {
            throw PasswordMismatchException("As senhas não conferem")
        }

        val encryptedPassword = passwordEncoder.encode(userDTO.passwordField)

        val user = User(
            role = userDTO.role,
            login = userDTO.login,
            name = userDTO.name,
            email = userDTO.email,
            password = encryptedPassword,
            confirmedPassword = encryptedPassword
        )

        val savedUser = userRepository.save(user)
        return converter.parseObject(savedUser, UserDTO::class.java)
    }

    override fun getUserById(id: Long): UserDTO {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("Usuário com id $id não encontrado") }

        return converter.parseObject(user, UserDTO::class.java)
    }

    override fun getAllUsers(): List<UserDTO> {
        return userRepository.findAll()
            .map { converter.parseObject(it, UserDTO::class.java) }
    }

    @Transactional
    override fun updateUser(id: Long, userDTO: UserDTO): UserDTO {
        val existingUser = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("Usuário com id $id não encontrado") }

        if (userDTO.passwordField != userDTO.confirmedPassword) {
            throw PasswordMismatchException("As senhas não conferem")
        }

        existingUser.apply {
            name = userDTO.name
            email = userDTO.email
            passwordFild = userDTO.passwordField
        }

        val updatedUser = userRepository.save(existingUser)
        return converter.parseObject(updatedUser, UserDTO::class.java)
    }

    @Transactional
    override fun deleteUser(id: Long): String {
        val user = userRepository.findById(id)
            .orElseThrow { UserNotFoundException("Usuário com id $id não encontrado") }

        userRepository.delete(user)
        return "Usuário ${user.login} deletado com sucesso!"
    }
}
