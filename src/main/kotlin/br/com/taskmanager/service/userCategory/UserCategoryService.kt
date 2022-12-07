package br.com.taskmanager.service.userCategory

import br.com.taskmanager.data.userCategory.UserCategoryEntity
import org.springframework.stereotype.Service

@Service
interface UserCategoryService {
    fun save(entity: UserCategoryEntity): Long
    fun update(id: Long, entity: UserCategoryEntity): Long
    fun findAll(): List<UserCategoryEntity>
    fun delete(id: Long): Long
}