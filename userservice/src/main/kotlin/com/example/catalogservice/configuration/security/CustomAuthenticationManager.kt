package com.example.userservice.configuration.security

import com.example.userservice.application.UserService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component
import javax.naming.AuthenticationException

@Component
class CustomAuthenticationManager(
    val userService: UserService,
    val passwordEncoder: PasswordEncoder
) : AuthenticationManager {
    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val userDetails: UserDetails =
            userService.loadUserByUsername(authentication.name)
        if (!passwordEncoder.matches(
                authentication.credentials.toString(),
                userDetails.password
            )
        ) {
            throw BadCredentialsException("Wrong password")
        }
        return UsernamePasswordAuthenticationToken(
            userDetails.username,
            userDetails.password,
            userDetails.authorities
        )
    }
}