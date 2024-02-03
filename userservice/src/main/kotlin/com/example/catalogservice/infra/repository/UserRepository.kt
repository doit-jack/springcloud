package com.example.userservice.infra.repository

import com.example.userservice.domain.UserEntity
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserEntity, Long> {
    fun findByUserId(userId: String): UserEntity?
    fun findByEmail(username: String): UserEntity?
}