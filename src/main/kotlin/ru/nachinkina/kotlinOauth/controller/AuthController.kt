package ru.nachinkina.kotlinOauth.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import ru.nachinkina.kotlinOauth.entity.User
import ru.nachinkina.kotlinOauth.jwt.JwtProvider
import ru.nachinkina.kotlinOauth.jwt.JwtResponse
import ru.nachinkina.kotlinOauth.jwt.ResponseMessage
import ru.nachinkina.kotlinOauth.DTO.LoginUser
import ru.nachinkina.kotlinOauth.DTO.NewUser
import ru.nachinkina.kotlinOauth.repository.RoleRepository
import ru.nachinkina.kotlinOauth.repository.UserRepository
import java.util.*

@CrossOrigin(origins = ["*"], maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController {

    @Autowired
    lateinit var authenticationManager: AuthenticationManager

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var roleRepository: RoleRepository

    @Autowired
    lateinit var encoder: PasswordEncoder

    @Autowired
    lateinit var jwtProvider: JwtProvider

    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: LoginUser): ResponseEntity<*> {

        val userCandidate: Optional<User> = userRepository.findByUsername(loginRequest.username!!)

        if (userCandidate.isPresent) {
            val user: User = userCandidate.get()
            val authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
            )
            SecurityContextHolder.getContext().setAuthentication(authentication)
            val jwt: String = jwtProvider.generateJwtToken(user.username!!)
            val authorities: List<GrantedAuthority> = listOf(SimpleGrantedAuthority(user.role!!.name))
            return ResponseEntity.ok(JwtResponse(jwt, user.username, authorities))
        } else {
            return ResponseEntity(
                ResponseMessage("User not found!"),
                HttpStatus.BAD_REQUEST)
        }
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody newUser: NewUser): ResponseEntity<*> {

        val userCandidate: Optional <User> = userRepository.findByUsername(newUser.username!!)

        if (!userCandidate.isPresent) {

            // Creating user's account
            val user = User(username = newUser.username,
                password = encoder.encode(newUser.password)
            )

            user.role = roleRepository.findByName("ROLE_USER")

            userRepository.save(user)

            return ResponseEntity(ResponseMessage("User registered successfully!"), HttpStatus.OK)
        } else {
            return ResponseEntity(ResponseMessage("User already exists!"),
                HttpStatus.BAD_REQUEST)
        }
    }

    private fun usernameExists(username: String): Boolean {
        return userRepository.findByUsername(username).isPresent
    }
}