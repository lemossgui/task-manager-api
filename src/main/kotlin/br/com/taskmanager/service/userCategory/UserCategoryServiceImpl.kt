package br.com.taskmanager.service.userCategory

import br.com.taskmanager.controller.exception.DuplicateEntityException
import br.com.taskmanager.controller.exception.NotFoundException
import br.com.taskmanager.data.user.UserEntity
import br.com.taskmanager.data.user.UserRepository
import br.com.taskmanager.data.userCategory.UserCategoryEntity
import br.com.taskmanager.data.userCategory.UserCategoryRepository
import br.com.taskmanager.service.authenticationInfo.AuthenticationInfo
import org.springframework.stereotype.Service

@Service
class UserCategoryServiceImpl(
        private val repository: UserCategoryRepository,
        private val userRepository: UserRepository,
        private val authenticationInfo: AuthenticationInfo,
) : UserCategoryService {

    private fun getUserId(): Long {
        return authenticationInfo.userId
    }

    private fun getUser(): UserEntity {
        return userRepository.getOne(getUserId())
    }

    override fun save(entity: UserCategoryEntity): Long {
        validateToSave(entity)
        entity.also { it.user = getUser() }
        val savedEntity = repository.save(entity)
        return savedEntity.id
    }

    override fun update(id: Long, entity: UserCategoryEntity): Long {
        validateToUpdate(id, entity)
        entity.also {
            it.id = id
            it.user = getUser()
        }
        val savedEntity = repository.save(entity)
        return savedEntity.id
    }

    override fun findAll(): List<UserCategoryEntity> {
        val userId = authenticationInfo.userId
        return repository.findAllByUserId(userId)
    }

    override fun delete(id: Long): Long {
        val found = findById(id)
        found.also { it.isExcluded = true }
        val savedEntity = repository.save(found)
        return savedEntity.id
    }

    private fun findById(id: Long): UserCategoryEntity {
        val found = repository.findById(id)
        if (!found.isPresent) {
            throw NotFoundException("Categoria não encontrada")
        }
        return found.get()
    }

    fun validateToSave(entity: UserCategoryEntity) {
        if(repository.existsByUserIdAndDescription(getUserId(), entity.description)) {
            throw DuplicateEntityException("Já existe uma categoria com esta descrição")
        }
        validateColorToSave(entity.color)
    }

    fun validateToUpdate(id: Long, entity: UserCategoryEntity) {
        if(repository.existsByIdDiffAndUserIdAndDescription(id, getUserId(), entity.description)) {
            throw DuplicateEntityException("Já existe uma categoria com esta descrição")
        }
        validateColorToUpdate(id, entity.color)
    }

    fun validateColorToSave(color: String) {
        val colors = repository.findAllColorsByUserId(getUserId())
        if (colors.contains(color)) {
            throw DuplicateEntityException("Esta cor está sendo usada por outra categoria")
        }
    }

    fun validateColorToUpdate(id: Long, color: String) {
        val colors = repository.findAllColorsByIdDiffAndUserId(id, getUserId())
        if (colors.contains(color)) {
            throw DuplicateEntityException("Esta cor está sendo usada por outra categoria")
        }
    }
}