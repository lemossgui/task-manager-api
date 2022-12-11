package br.com.taskmanager.controller.user

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class UserPasswordSaveDto(
        var oldPassword: String,
        var newPassword: String,
        var newPasswordConfirmation: String,
)