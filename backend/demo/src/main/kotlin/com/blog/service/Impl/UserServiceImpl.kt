package com.blog.service.Impl
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
    private val converter: EntityConverter) : UserService {

    @Transactional
    override fun createUser(userDTO: UserDTO): User {

        if(userRepository.findByEmail(userDTO.email ?: "") != null) {
            throw IllegalArgumentException("Email já cadastrado")
        }

        if (userDTO.passwordFild != userDTO.confirmedPassword) {
            throw IllegalArgumentException("Senhas não conferem")
        }

        val encryptedPassword = BCryptPasswordEncoder().encode(userDTO.passwordFild)

        val user = User(
            role = userDTO.role,
            login = userDTO.login,
            name = userDTO.name,
            email = userDTO.email,
            password = encryptedPassword,
            confirmedPassword = encryptedPassword
        )

        return userRepository.save(user)
    }


    override fun getUserById(id: Long): User? {
        return userRepository.getUserById(id);
    }

    override fun getAllUsers(): List<UserDTO> {
        return userRepository.findAll().map { user ->
            UserDTO(
                name = user.name ?: "",
                email = user.email ?: "",
                passwordFild = user.password ?: ""
            )
        }
    }

    override fun updateUser(id: Long, userDTO: UserDTO): UserDTO {
        val existingUser = userRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Usuário com id $id não encontrado") }


        existingUser.apply {
            name = userDTO.name
            email = userDTO.email
            passwordFild = userDTO.passwordFild
        }

       val updateUser = userRepository.save(existingUser)

       return converter.parseObject(updateUser, UserDTO::class.java)
    }

    override fun deleteUser(id: Long): String {
        val user = userRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Usuário com id $id não encontrado") }

        userRepository.delete(user)
        return "Usuário ${user.username} deletado com sucesso!"
    }
}