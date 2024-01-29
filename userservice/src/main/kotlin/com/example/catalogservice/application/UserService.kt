package com.example.userservice.application

import com.example.userservice.domain.UserEntity
import com.example.userservice.dto.UserDto
import org.springframework.security.core.userdetails.UserDetailsService

interface UserService: UserDetailsService {
    fun createUser(userDto: UserDto): UserDto
    fun getUserByUserId(userId: String): UserDto
    fun getUserByEmail(email: String): UserDto
    fun getUserByAll(): Iterable<UserEntity>
}