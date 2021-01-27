package ru.nachinkina.kotlinOauth.handler

import org.springframework.stereotype.Service
import ru.nachinkina.kotlinOauth.entity.Person
import ru.nachinkina.kotlinOauth.repository.PersonRepository
import ru.nachinkina.kotlinOauth.handler.PersonService

@Service
class PersonServiceImpl(val personRepository: PersonRepository) : PersonService {
    override fun read() : Iterable<Person>{
        return personRepository.findAll()
    }
}