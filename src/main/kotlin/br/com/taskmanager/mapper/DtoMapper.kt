package br.com.taskmanager.mapper

import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class DtoMapper<E, D>(
        private val dtoClass: Class<D>,
) {

    @Autowired
    lateinit var mapper: ObjectMapperUtils

    fun mapToDto(entity: E): D {
        return mapper.map(entity, dtoClass)
    }

    fun mapToDtoList(list: List<E>): List<D> {
        return list.map(::mapToDto)
    }
}