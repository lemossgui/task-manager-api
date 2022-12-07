package br.com.taskmanager.controller.priority

import br.com.taskmanager.data.priority.PriorityEntity
import br.com.taskmanager.mapper.DtoMapper
import org.springframework.stereotype.Component

@Component
class PriorityDtoMapper : DtoMapper<PriorityEntity, PriorityDto>(
        dtoClass = PriorityDto::class.java,
)