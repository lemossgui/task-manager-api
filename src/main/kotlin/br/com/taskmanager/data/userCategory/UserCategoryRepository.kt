package br.com.taskmanager.data.userCategory

import br.com.taskmanager.data.abstractRepository.BaseRepository
import org.springframework.data.jpa.repository.Query

interface UserCategoryRepository : BaseRepository<UserCategoryEntity> {

    @Query("SELECT e " +
            "FROM UserCategoryEntity e " +
            "WHERE e.user.id = :userId " +
            "AND e.isExcluded = false")
    fun findAllByUserId(userId: Long): MutableList<UserCategoryEntity>

    @Query("SELECT COUNT(e) > 0 " +
            "FROM UserCategoryEntity e " +
            "WHERE e.user.id = :userId " +
            "AND UPPER(e.description) = UPPER(:description) " +
            "AND e.isExcluded = false")
    fun existsByUserIdAndDescription(userId: Long, description: String): Boolean

    @Query("SELECT COUNT(e) > 0 " +
            "FROM UserCategoryEntity e " +
            "WHERE e.id != :id " +
            "AND e.user.id = :userId " +
            "AND UPPER(e.description) = UPPER(:description) " +
            "AND e.isExcluded = false")
    fun existsByIdDiffAndUserIdAndDescription(id: Long, userId: Long, description: String): Boolean

    @Query("SELECT e.color " +
            "FROM UserCategoryEntity e " +
            "WHERE e.user.id = :userId " +
            "AND e.isExcluded = false")
    fun findAllColorsByUserId(userId: Long): MutableList<String>

    @Query("SELECT e.color " +
            "FROM UserCategoryEntity e " +
            "WHERE e.id != :id " +
            "AND e.user.id = :userId " +
            "AND e.isExcluded = false")
    fun findAllColorsByIdDiffAndUserId(id: Long, userId: Long): MutableList<String>
}