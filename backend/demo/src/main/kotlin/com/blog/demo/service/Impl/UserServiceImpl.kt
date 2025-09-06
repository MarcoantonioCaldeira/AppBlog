package com.blog.demo.service.Impl
import com.blog.demo.model.User
import com.blog.demo.model.dto.UserDTO
import com.blog.demo.repository.UserRepository
import com.blog.demo.service.UserService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {

    @Transactional
    override fun createUser(userDTO: UserDTO): User {
        if (userRepository.getUserById(userDTO.id ?: 0) != null) {
            throw IllegalArgumentException("Usuário já existe")
        }

        if(userRepository.findByEmail(userDTO.email ?: "") != null) {
            throw IllegalArgumentException("Email já cadastrado")
        }

        if (userDTO.password != userDTO.confirmedPassword) {
            throw IllegalArgumentException("Senhas não conferem")
        }

        val encryptedPassword = BCryptPasswordEncoder().encode(userDTO.password)

        val user = User(
            username = userDTO.username,
            email = userDTO.email,
            password = encryptedPassword,
            confirmedPassword = encryptedPassword
        )

        return userRepository.save(user)
    }


    override fun getUserById(id: Long): UserDTO {
        return userRepository.getUserById(id);
    }

    override fun getAllUsers(): List<UserDTO> {
        return userRepository.findAll().map { user ->
            UserDTO(
                id = user.id,
                username = user.username ?: "",
                email = user.email ?: "",
                password = user.password ?: "",
                confirmedPassword = user.confirmedPassword ?: ""
            )
        }
    }

    override fun updateUser(id: Long, userDTO: UserDTO): UserDTO {
        val existingUser = userRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Usuário com id $id não encontrado") }


        existingUser.apply {
            username = userDTO.username
            email = userDTO.email
            password = userDTO.password
        }

        return userRepository.save(existingUser).let { updatedUser ->
            UserDTO(
                id = updatedUser.id,
                username = updatedUser.username ?: "",
                email = updatedUser.email ?: "",
                password = updatedUser.password ?: "",
                confirmedPassword = updatedUser.confirmedPassword ?: ""
            )
        }
    }

    override fun deleteUser(id: Long): String {
        val user = userRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Usuário com id $id não encontrado") }

        userRepository.delete(user)
        return "Usuário ${user.username} deletado com sucesso!"
    }
}