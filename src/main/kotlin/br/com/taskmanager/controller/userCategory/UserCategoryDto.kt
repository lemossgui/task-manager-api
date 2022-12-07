package br.com.taskmanager.controller.userCategory

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class UserCategoryDto(
        var id: Long,
        var description: String,
        var color: String,
)