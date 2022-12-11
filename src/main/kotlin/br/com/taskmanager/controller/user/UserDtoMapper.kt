package br.com.taskmanager.controller.user

import br.com.taskmanager.data.user.UserEntity
import br.com.taskmanager.mapper.DtoMapper
import org.springframework.stereotype.Component

@Component
class UserDtoMapper : DtoMapper<UserEntity, UserDto>(
        dtoClass = UserDto::class.java,
)