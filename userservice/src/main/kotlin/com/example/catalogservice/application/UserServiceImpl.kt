@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.userservice.application

import com.example.core.utils.log
import com.example.userservice.controller.dto.UserDto
import com.example.userservice.domain.UserEntity
import com.example.userservice.infra.client.OrderServiceClient
import com.example.userservice.infra.repository.UserRepository
import feign.FeignException
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.*

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: BCryptPasswordEncoder,
    val restTemplate: RestTemplate,
    val orderServiceClient: OrderServiceClient
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

//        val orderUrl = "http://ORDER-SERVICE/${userId}/orders"
//        val exchange =
//            restTemplate.exchange<List<ResponseOrder>>(orderUrl, HttpMethod.GET, null).body
//        userDto.orders = exchange.orEmpty()

        userDto.orders = orderServiceClient.getOrders(userId)

//        FeignErrorDecoder 구현으로 try catch 불필요
//        try {
//            userDto.orders = orderServiceClient.getOrdersNg(userId)
//        } catch (ex: FeignException) {
//            log().error(ex.message)
//        }

//        userDto.orders = orderServiceClient.getOrdersNg(userId)


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
