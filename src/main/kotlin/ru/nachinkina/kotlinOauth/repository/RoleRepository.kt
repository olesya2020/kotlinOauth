package ru.nachinkina.kotlinOauth.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import ru.nachinkina.kotlinOauth.entity.Role

interface RoleRepository : JpaRepository<Role, Long> {

    fun findByName(@Param("name") name: String): Role

}