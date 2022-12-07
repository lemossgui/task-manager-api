package br.com.taskmanager.mapper

import org.springframework.beans.factory.annotation.Autowired
import javax.persistence.MappedSuperclass

@MappedSuperclass
abstract class SaveDtoMapper<D, E>(
        private val entityClass: Class<E>,
) {

    @Autowired
    lateinit var mapper: ObjectMapperUtils

    fun mapToEntity(dto: D): E {
        return mapper.map(dto, entityClass)
    }

    fun mapToEntityList(list: List<D>): List<E> {
        return list.map(::mapToEntity)
    }
}