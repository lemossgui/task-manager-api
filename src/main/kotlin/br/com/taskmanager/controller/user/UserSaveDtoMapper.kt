package br.com.taskmanager.controller.user

import br.com.taskmanager.data.user.UserEntity
import br.com.taskmanager.mapper.SaveDtoMapper
import org.springframework.stereotype.Component

@Component
class UserSaveDtoMapper : SaveDtoMapper<UserSaveDto, UserEntity>(
        entityClass = UserEntity::class.java,
)
