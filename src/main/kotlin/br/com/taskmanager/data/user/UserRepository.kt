package br.com.taskmanager.data.user

import br.com.taskmanager.data.abstractRepository.BaseRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : BaseRepository<UserEntity> {

    @Query("SELECT e " +
            "FROM UserEntity e " +
            "WHERE e.email = :email " +
            "AND e.password = :password " +
            "AND e.isExcluded = false")
    fun findByEmailAndPassword(email: String, password: String): Optional<UserEntity>

    @Query("SELECT COUNT(e) > 0 " +
            "FROM UserEntity e " +
            "WHERE e.email = :email " +
            "AND e.isExcluded = false")
    fun existsByEmail(email: String): Boolean
}