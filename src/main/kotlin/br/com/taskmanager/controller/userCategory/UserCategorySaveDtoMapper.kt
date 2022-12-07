package br.com.taskmanager.controller.userCategory

import br.com.taskmanager.data.userCategory.UserCategoryEntity
import br.com.taskmanager.mapper.SaveDtoMapper
import org.springframework.stereotype.Component

@Component
class UserCategorySaveDtoMapper : SaveDtoMapper<UserCategorySaveDto, UserCategoryEntity>(
        entityClass = UserCategoryEntity::class.java,
)