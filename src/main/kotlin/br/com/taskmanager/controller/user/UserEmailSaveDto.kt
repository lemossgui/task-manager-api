package br.com.taskmanager.controller.user

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class UserEmailSaveDto(
        var email: String,
)