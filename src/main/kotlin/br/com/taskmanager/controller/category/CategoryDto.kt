package br.com.taskmanager.controller.category

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class CategoryDto(
        var id: Long,
        var description: String,
        var colorKey: String,
)