@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.userservice.application

import com.example.userservice.domain.UserEntity
import com.example.userservice.dto.UserDto
import com.example.userservice.repository.UserRepository
import com.example.userservice.vo.ResponseOrder
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: BCryptPasswordEncoder
) : UserService {
    override fun createUser(userDto: UserDto): UserDto {
        userDto.userId = UUID.randomUUID().toString()
        val mapper = ModelMapper()
        mapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        val userEntity: UserEntity = mapper.map(userDto, UserEntity::class.java)
        userEntity.encryptedPwd = passwordEncoder.encode(userDto.pwd)

        userRepository.save(userEntity)
        return userDto
    }

    override fun getUserByUserId(userId: String): UserDto {
        val userEntity =
            userRepository.findByUserId(userId) ?: throw UsernameNotFoundException("User not found")
        val userDto = ModelMapper().map(userEntity, UserDto::class.java)
        val orders = listOf<ResponseOrder>()
        userDto.orders = orders
        return userDto
    }

    override fun getUserByEmail(email: String): UserDto {
        val userEntity =
            userRepository.findByEmail(email) ?: throw UsernameNotFoundException("User not found")
        val userDto = ModelMapper().map(userEntity, UserDto::class.java)
        return userDto
    }

    override fun getUserByAll(): Iterable<UserEntity> {
        return userRepository.findAll()
    }

    override fun loadUserByUsername(email: String): UserDetails {
        val userEntity =
            userRepository.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return User(userEntity.email, userEntity.encryptedPwd, true, true, true, true, listOf())
    }

}
