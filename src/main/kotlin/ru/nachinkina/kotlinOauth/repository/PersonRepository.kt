package ru.nachinkina.kotlinOauth.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.nachinkina.kotlinOauth.entity.Person

@Repository
interface PersonRepository: CrudRepository<Person, Long> {
}