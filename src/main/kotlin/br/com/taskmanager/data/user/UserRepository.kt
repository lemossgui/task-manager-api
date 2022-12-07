package br.com.taskmanager.data.user

import br.com.taskmanager.data.abstractRepository.BaseRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : BaseRepository<UserEntity> {

    @Query("SELECT e " +
            "FROM UserEntity e " +
            "WHERE (e.username = :identifier OR e.email = :identifier) " +
            "AND e.password = :password " +
            "AND e.isExcluded = false")
    fun findByIdentifierAndPassword(identifier: String, password: String): Optional<UserEntity>

    @Query("SELECT e FROM UserEntity e WHERE e.username = :username AND e.isExcluded = false")
    fun findByUsername(username: String): Optional<UserEntity>

    @Query("SELECT e FROM UserEntity e WHERE e.email = :email AND e.isExcluded = false")
    fun findByEmail(email: String): Optional<UserEntity>
}