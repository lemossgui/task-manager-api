package br.com.taskmanager.service.user

import br.com.taskmanager.controller.exception.DuplicateEntityException
import br.com.taskmanager.data.defaultCategory.DefaultCategoryRepository
import br.com.taskmanager.data.user.UserEntity
import br.com.taskmanager.data.user.UserRepository
import br.com.taskmanager.data.userCategory.UserCategoryEntity
import br.com.taskmanager.data.userCategory.UserCategoryRepository
import br.com.taskmanager.utils.md5
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserServiceImpl(
        private val repository: UserRepository,
        private val defaultCategoryRepository: DefaultCategoryRepository,
        private val userCategoryRepository: UserCategoryRepository,
) : UserService {

    @Transactional
    override fun save(entity: UserEntity): Long {
        validateToSave(entity)
        entity.also { it.password = it.password.md5() }
        val savedEntity = repository.save(entity)
        createUserCategories(savedEntity)
        return savedEntity.id
    }

    fun validateToSave(entity: UserEntity) {
        if (repository.existsByUsername(entity.username)) {
            throw DuplicateEntityException("Já existe um usuário cadastrado com este username")
        }

        if (repository.existsByEmail(entity.email)) {
            throw DuplicateEntityException("Já existe um usuário cadastrado com este e-mail")
        }
    }

    /**
     * Irá criar as categorias do usuário usando algumas categorias pré-definidas do sistema.
     * São criadas categorias para o usuário para que ele possa personalizar suas categorias.
     */
    fun createUserCategories(user: UserEntity) {
        val defaultCategories = defaultCategoryRepository.findAll()

        val userCategories = defaultCategories.map {
            UserCategoryEntity(
                    description = it.description,
                    color = it.color,
                    user = user,
            )
        }

        userCategoryRepository.saveAll(userCategories)
    }
}