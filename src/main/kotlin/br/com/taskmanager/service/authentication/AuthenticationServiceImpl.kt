package br.com.taskmanager.service.authentication

import br.com.taskmanager.controller.exception.NotFoundException
import br.com.taskmanager.data.user.UserRepository
import br.com.taskmanager.security.JwtUtil
import br.com.taskmanager.security.PayloadToken
import br.com.taskmanager.utils.md5
import org.springframework.stereotype.Service

@Service
class AuthenticationServiceImpl(
        private val userRepository: UserRepository,
        private val jwtUtil: JwtUtil,
) : AuthenticationService {

    override fun doLogin(email: String, password: String): String {
        val found = userRepository.findByEmailAndPassword(email, password.md5())

        if (found.isPresent) {
            val user = found.get()
            val payloadToken = PayloadToken(
                    id = user.id,
                    name = user.name,
            )
            return jwtUtil.generateToken(payloadToken)
        }

        throw NotFoundException("E-mail e/ou senha incorretos")
    }
}