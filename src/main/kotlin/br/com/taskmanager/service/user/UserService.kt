package br.com.taskmanager.service.user

import br.com.taskmanager.data.user.UserEntity
import org.springframework.stereotype.Service

@Service
interface UserService {
    fun save(entity: UserEntity): String
    fun updateName(id: Long, name: String): Long
    fun updateEmail(id: Long, email: String): Long
    fun updatePassword(id: Long, oldPassword: String, newPassword: String, newPasswordConfirmation: String): Long
    fun updateReceivingNotification(id: Long, notificationIsEnable: Boolean): Long
    fun getLoggedUser(): UserEntity
}