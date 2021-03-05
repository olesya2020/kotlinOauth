package ru.nachinkina.kotlinOauth.handler

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import ru.nachinkina.kotlinOauth.entity.User
import ru.nachinkina.kotlinOauth.repository.UserRepository
import java.util.*
import kotlin.jvm.Throws

//Для определения ролей пользователей нам необходим
// дополнительный сервис, реализующий интерфейс UserDetailsService
@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {

        val userOptional: Optional<User> = userRepository.findByUsername(username)

        if(!userOptional.isPresent)
             throw UsernameNotFoundException("User '$username' not found")
        val user = userOptional.get();
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