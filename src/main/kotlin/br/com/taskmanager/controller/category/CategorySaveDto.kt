package br.com.taskmanager.controller.category

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class CategorySaveDto(
        var description: String,
        var colorKey: String,
)