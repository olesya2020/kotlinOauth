package ru.nachinkina.kotlinOauth.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.nachinkina.kotlinOauth.repository.UserRepository
import kotlin.jvm.Throws

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {

        val user = userRepository.findByUsername(username).get()

        if(user.username == null)
             throw UsernameNotFoundException("User '$username' not found")

        val authorities: List<GrantedAuthority> = listOf(SimpleGrantedAuthority(user.role!!.name))

        return org.springframework.security.core.userdetails.User
            .withUsername(username)
            .password(user.password)
            .authorities(authorities)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()

    }
}