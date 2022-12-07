package br.com.taskmanager.service.authenticationInfo

import br.com.taskmanager.controller.exception.ForbiddenException
import br.com.taskmanager.security.PayloadToken
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class AuthenticationInfo {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    private val payloadToken: PayloadToken
        get() {
            try {
                val authentication = SecurityContextHolder.getContext().authentication
                return authentication.principal as PayloadToken
            } catch (_: Exception) {
                logger.info("Falha na autenticação, acesso negado")
                throw ForbiddenException("Falha na autenticação, acesso negado")
            }
        }

    val userId: Long
        get() = payloadToken.id
}