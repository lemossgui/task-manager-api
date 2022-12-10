package br.com.taskmanager.controller.priority

import br.com.taskmanager.utils.NoArgs

@NoArgs
data class PriorityDto(
        var id: Long,
        var description: String,
        var key: String,
        var index: Int,
)