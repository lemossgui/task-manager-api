package br.com.taskmanager.service.user

import br.com.taskmanager.controller.exception.DuplicateEntityException
import br.com.taskmanager.data.user.UserEntity
import br.com.taskmanager.data.user.UserRepository
import br.com.taskmanager.utils.md5
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
        private val repository: UserRepository,
) : UserService {

    override fun save(entity: UserEntity): Long {
        if (repository.findByUsername(entity.username).isPresent) {
            throw DuplicateEntityException("Já existe um usuário cadastrado com este username")
        }

        if (repository.findByEmail(entity.email).isPresent) {
            throw DuplicateEntityException("Já existe um usuário cadastrado com este e-mail")
        }

        entity.also { it.password = it.password.md5() }

        val savedEntity = repository.save(entity)

        return savedEntity.id
    }
}