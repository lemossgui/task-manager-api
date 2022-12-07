package br.com.taskmanager.data.abstractRepository

import br.com.taskmanager.data.abstractEntity.AbstractEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.NoRepositoryBean
import java.util.*

@NoRepositoryBean
interface BaseRepository<E : AbstractEntity> : JpaRepository<E, Long> {

    @Query("SELECT e FROM #{#entityName} e WHERE e.id = :id AND e.isExcluded = false")
    override fun findById(id: Long): Optional<E>

    @Query("SELECT e FROM #{#entityName} e WHERE e.isExcluded = false")
    override fun findAll(): MutableList<E>

    @Query("SELECT e FROM #{#entityName} e WHERE e.isExcluded = false")
    override fun findAll(pageable: Pageable): Page<E>
}