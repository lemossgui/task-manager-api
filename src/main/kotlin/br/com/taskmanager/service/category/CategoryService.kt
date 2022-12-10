package br.com.taskmanager.service.category

import br.com.taskmanager.data.category.CategoryEntity
import org.springframework.stereotype.Service

@Service
interface CategoryService {
    fun save(entity: CategoryEntity): Long
    fun update(id: Long, entity: CategoryEntity): Long
    fun findAll(): List<CategoryEntity>
    fun delete(id: Long): Long
}