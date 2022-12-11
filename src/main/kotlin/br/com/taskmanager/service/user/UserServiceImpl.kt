package br.com.taskmanager.service.user

import br.com.taskmanager.controller.exception.BadRequestException
import br.com.taskmanager.controller.exception.DuplicateEntityException
import br.com.taskmanager.data.defaultCategory.DefaultCategoryRepository
import br.com.taskmanager.data.user.UserEntity
import br.com.taskmanager.data.user.UserRepository
import br.com.taskmanager.data.category.CategoryEntity
import br.com.taskmanager.data.category.CategoryRepository
import br.com.taskmanager.security.JwtUtil
import br.com.taskmanager.security.PayloadToken
import br.com.taskmanager.service.authenticationInfo.AuthenticationInfo
import br.com.taskmanager.utils.md5
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserServiceImpl(
        private val repository: UserRepository,
        private val defaultCategoryRepository: DefaultCategoryRepository,
        private val userCategoryRepository: CategoryRepository,
        private val authenticationInfo: AuthenticationInfo,
        private val jwtUtil: JwtUtil,
) : UserService {

    @Transactional
    override fun save(entity: UserEntity): String {
        validateToSave(entity)
        entity.also {
            it.password = it.password.md5()
            it.notificationIsEnable = true
        }
        val savedEntity = repository.save(entity)

        createUserCategories(savedEntity)

        val payloadToken = PayloadToken(
                id = savedEntity.id,
                name = savedEntity.name,
        )
        return jwtUtil.generateToken(payloadToken)
    }

    override fun updateName(id: Long, name: String): Long {
        val user = repository.getOne(id)
        user.also { it.name = name }
        val savedEntity = repository.save(user)
        return savedEntity.id
    }

    override fun updateEmail(id: Long, email: String): Long {
        val user = repository.getOne(id)

        if (user.email == email) {
            throw BadRequestException("Informe um e-mail diferente do seu e-mail atual")
        }

        if (repository.existsByEmail(email)) {
            throw DuplicateEntityException("Já existe um usuário cadastrado com este e-mail")
        }

        user.also { it.email = email }
        val savedEntity = repository.save(user)
        return savedEntity.id
    }

    override fun updatePassword(id: Long, oldPassword: String, newPassword: String, newPasswordConfirmation: String): Long {
        val user = repository.getOne(id)

        if (user.password != oldPassword.md5()) {
            throw BadRequestException("Senha incorreta")
        } else if(newPassword != newPasswordConfirmation) {
            throw BadRequestException("As senhas não conferem")
        } else if(user.password == newPassword.md5()) {
            throw BadRequestException("Informe uma senha diferente da sua senha atual")
        }

        user.also { it.password = newPassword.md5() }
        val savedEntity = repository.save(user)
        return savedEntity.id
    }

    override fun updateReceivingNotification(id: Long, notificationIsEnable: Boolean): Long {
        val user = repository.getOne(id)
        user.also { it.notificationIsEnable = notificationIsEnable }
        val savedEntity = repository.save(user)
        return savedEntity.id
    }

    override fun getLoggedUser(): UserEntity {
        val userId = authenticationInfo.userId
        return repository.getOne(userId)
    }

    fun validateToSave(entity: UserEntity) {
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
            CategoryEntity(
                    description = it.description,
                    colorKey = it.colorKey,
                    user = user,
            )
        }

        userCategoryRepository.saveAll(userCategories)
    }
}