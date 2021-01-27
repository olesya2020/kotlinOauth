package ru.nachinkina.kotlinOauth.handler

import ru.nachinkina.kotlinOauth.entity.Person

interface PersonService {
    fun read() : Iterable<Person>
}