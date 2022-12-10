package br.com.taskmanager.data.priority

import br.com.taskmanager.data.abstractRepository.BaseRepository
import org.springframework.data.jpa.repository.Query

interface PriorityRepository : BaseRepository<PriorityEntity> {

    @Query("SELECT e " +
            "FROM PriorityEntity e " +
            "WHERE e.isExcluded = false " +
            "ORDER BY e.index ASC")
    override fun findAll(): MutableList<PriorityEntity>
}