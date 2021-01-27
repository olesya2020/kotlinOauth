package ru.nachinkina.kotlinOauth.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import ru.nachinkina.kotlinOauth.entity.User
import java.util.*
import javax.transaction.Transactional

interface UserRepository : JpaRepository<User, Long>{

    fun findByUsername(@Param("username") username: String): Optional<User>

    fun existsByUsername(@Param("username") username: String): Boolean

    @Transactional
    fun deleteByUsername(@Param("username") username: String)
}