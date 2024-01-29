package com.example.core.utils

import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Component
class JwtUtils(
    @Value("\${token.secret}") val secretKey: String,
    @Value("\${token.expiration_time}") val expirationTime: Long,
) {
    private val signingKey: SecretKey =
        Keys.hmacShaKeyFor(secretKey.toByteArray(StandardCharsets.UTF_8))

    fun generateJwt(subject: String): String = Jwts.builder()
        .setSubject(subject)
        .setExpiration(Date(System.currentTimeMillis() + expirationTime))
        .signWith(signingKey)
        .compact()

    fun isJwtValid(token: String): Boolean {
        try {
            val subject = jwtParser().parseClaimsJws(token).body.subject
            return !subject.isNullOrEmpty()
        } catch (ex: Exception) {
            return false
        }
    }

    fun jwtParser(): JwtParser = Jwts.parserBuilder().setSigningKey(signingKey).build()
}