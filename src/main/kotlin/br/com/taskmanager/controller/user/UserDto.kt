package br.com.taskmanager.controller.user

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class UserDto(
        var id: Long,
        var name: String,
        var email: String,
        var notificationIsEnable: Boolean,
)