package br.com.taskmanager.controller.category

import br.com.taskmanager.data.category.CategoryEntity
import br.com.taskmanager.mapper.SaveDtoMapper
import org.springframework.stereotype.Component

@Component
class CategorySaveDtoMapper : SaveDtoMapper<CategorySaveDto, CategoryEntity>(
        entityClass = CategoryEntity::class.java,
)