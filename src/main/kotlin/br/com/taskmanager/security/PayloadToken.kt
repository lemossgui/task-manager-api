package br.com.taskmanager.security

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class PayloadToken(
        var id: Long,
        var name: String,
)