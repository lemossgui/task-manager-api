package br.com.taskmanager.service.user

import br.com.taskmanager.data.user.UserEntity
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun save(entity: UserEntity): Long
}