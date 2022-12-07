package br.com.taskmanager.controller.userCategory

import br.com.taskmanager.data.userCategory.UserCategoryEntity
import br.com.taskmanager.mapper.DtoMapper
import org.springframework.stereotype.Component

@Component
class UserCategoryDtoMapper : DtoMapper<UserCategoryEntity, UserCategoryDto>(
        dtoClass = UserCategoryDto::class.java,
)