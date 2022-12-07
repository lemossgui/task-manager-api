package br.com.taskmanager.controller.userCategory

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class UserCategorySaveDto(
        var description: String,
        var color: String,
)