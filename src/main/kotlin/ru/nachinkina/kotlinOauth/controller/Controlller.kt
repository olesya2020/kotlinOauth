package ru.nachinkina.kotlinOauth.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.nachinkina.kotlinOauth.entity.User
import ru.nachinkina.kotlinOauth.handler.Greeting
import ru.nachinkina.kotlinOauth.handler.PersonService
import ru.nachinkina.kotlinOauth.repository.UserRepository


@RestController
@RequestMapping("/api")
class Controlller(
    @Autowired
    val personService: PersonService,

    @Autowired
    val userRepository: UserRepository

){

    @GetMapping("/usercontent")
    @PreAuthorize("hasRole('USER')")
    @ResponseBody
    fun getUserContent(authentication: Authentication): String {
        val user: User = userRepository.findByUsername(authentication.name).get()
        return "Hello " + user.username +"!"
    }

    @GetMapping("/admincontent")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseBody
    fun getAdminContent(): String {
        return "Admin's content"
    }

    @GetMapping("/greeting")
    fun greeting(@RequestParam(value = "name") name: String) =
        Greeting(1, "Hello, $name")

    @GetMapping("/persons")
    fun persons() = personService.read()

}