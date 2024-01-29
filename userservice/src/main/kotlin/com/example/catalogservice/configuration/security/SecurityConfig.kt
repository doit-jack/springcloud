package com.example.userservice.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.core.userdetails.User.*
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.IpAddressMatcher


@Configuration
@EnableWebSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(
        http: HttpSecurity, authFilter: AuthenticationFilter
    ): SecurityFilterChain {
        http {
            csrf { disable() }
            httpBasic { }

            authorizeRequests {
                authorize(anyRequest, permitAll)
            }

            addFilterBefore<UsernamePasswordAuthenticationFilter>(authFilter)
        }
        return http.build()
    }

    private fun hasIpAddress(ipAddress: String): AuthorizationManager<RequestAuthorizationContext> {
        val ipAddressMatcher = IpAddressMatcher(ipAddress)
        return AuthorizationManager { _, context: RequestAuthorizationContext ->
            val request = context.request
            AuthorizationDecision(ipAddressMatcher.matches(request))
        }
    }
}