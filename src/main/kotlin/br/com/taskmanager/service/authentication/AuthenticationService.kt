package br.com.taskmanager.service.authentication

import org.springframework.stereotype.Service

@Service
interface AuthenticationService {
    fun doLogin(email: String, password: String): String
}