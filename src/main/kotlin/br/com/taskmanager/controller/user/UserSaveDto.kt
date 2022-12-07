package br.com.taskmanager.controller.user

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class UserSaveDto(
        var name: String,
        var username: String,
        var email: String,
        var password: String,
)