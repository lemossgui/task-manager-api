package br.com.taskmanager.controller.authentication

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class CredentialDto(
        var identifier: String,
        var password: String,
)