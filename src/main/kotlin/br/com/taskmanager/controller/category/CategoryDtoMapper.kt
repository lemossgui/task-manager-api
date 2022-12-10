package br.com.taskmanager.controller.category

import br.com.taskmanager.data.category.CategoryEntity
import br.com.taskmanager.mapper.DtoMapper
import org.springframework.stereotype.Component

@Component
class CategoryDtoMapper : DtoMapper<CategoryEntity, CategoryDto>(
        dtoClass = CategoryDto::class.java,
)