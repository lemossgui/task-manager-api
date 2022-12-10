package br.com.taskmanager.data.category

import br.com.taskmanager.data.abstractRepository.BaseRepository
import org.springframework.data.jpa.repository.Query

interface CategoryRepository : BaseRepository<CategoryEntity> {

    @Query("SELECT e " +
            "FROM CategoryEntity e " +
            "WHERE e.user.id = :userId " +
            "AND e.isExcluded = false " +
            "ORDER BY e.description ASC")
    fun findAllByUserId(userId: Long): MutableList<CategoryEntity>

    @Query("SELECT COUNT(e) > 0 " +
            "FROM CategoryEntity e " +
            "WHERE e.user.id = :userId " +
            "AND UPPER(e.description) = UPPER(:description) " +
            "AND e.isExcluded = false")
    fun existsByUserIdAndDescription(userId: Long, description: String): Boolean

    @Query("SELECT COUNT(e) > 0 " +
            "FROM CategoryEntity e " +
            "WHERE e.id != :id " +
            "AND e.user.id = :userId " +
            "AND UPPER(e.description) = UPPER(:description) " +
            "AND e.isExcluded = false")
    fun existsByIdDiffAndUserIdAndDescription(id: Long, userId: Long, description: String): Boolean

    @Query("SELECT e.colorKey " +
            "FROM CategoryEntity e " +
            "WHERE e.user.id = :userId " +
            "AND e.isExcluded = false")
    fun findAllColorsByUserId(userId: Long): MutableList<String>

    @Query("SELECT e.colorKey " +
            "FROM CategoryEntity e " +
            "WHERE e.id != :id " +
            "AND e.user.id = :userId " +
            "AND e.isExcluded = false")
    fun findAllColorsByIdDiffAndUserId(id: Long, userId: Long): MutableList<String>
}