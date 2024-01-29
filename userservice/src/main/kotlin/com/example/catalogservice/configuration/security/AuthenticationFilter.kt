package com.example.userservice.configuration.security

import com.example.core.utils.JwtUtils
import com.example.userservice.application.UserService
import com.example.userservice.vo.RequestLogin
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.annotation.PostConstruct
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class AuthenticationFilter(
    val userService: UserService,
    val authManager: CustomAuthenticationManager,
    val jwtUtils: JwtUtils
) : UsernamePasswordAuthenticationFilter() {
    @PostConstruct
    fun init() {
        authenticationManager = authManager
    }

    override fun attemptAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse
    ): Authentication {
        val cred = ObjectMapper().readValue(request.inputStream, RequestLogin::class.java)
        val token = UsernamePasswordAuthenticationToken(cred.email, cred.password, listOf())
        return authenticationManager.authenticate(token)
    }

    override fun successfulAuthentication(
        request: HttpServletRequest,
        response: HttpServletResponse,
        chain: FilterChain,
        authResult: Authentication
    ) {
        val username: String = authResult.name
        val userDto = userService.getUserByEmail(username)

        val token = jwtUtils.generateJwt(userDto.userId)

        response.addHeader("token", token)
        response.addHeader("userId", userDto.userId)
    }
}