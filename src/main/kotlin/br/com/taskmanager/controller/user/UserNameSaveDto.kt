package br.com.taskmanager.controller.user

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class UserNameSaveDto(
        var name: String,
)