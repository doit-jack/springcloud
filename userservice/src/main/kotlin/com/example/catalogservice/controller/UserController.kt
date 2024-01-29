package com.example.userservice.controller

import com.example.userservice.domain.UserEntity
import com.example.userservice.dto.UserDto
import com.example.userservice.application.UserService
import com.example.userservice.vo.RequestUser
import com.example.userservice.vo.ResponseUser
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userService: UserService,
    @Value("\${greeting.message}") val msg: String
) {

//    @Value("\${greeting.message}")
//    lateinit var msg: String

    @GetMapping("/health-check")
    fun status(): String = "It's Working"

    @GetMapping("/welcome")
    fun welcome(): String = msg

    @PostMapping("/users")
    fun createUser(
        @RequestBody user: RequestUser,
    ): ResponseEntity<ResponseUser> {
        val mapper = ModelMapper()
        mapper.apply {
            configuration.isFieldMatchingEnabled = true
            configuration.setMatchingStrategy(MatchingStrategies.STRICT)
            configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        }
        val userDto = mapper.map(user, UserDto::class.java)
        userService.createUser(userDto)
        val responseUser = mapper.map(userDto, ResponseUser::class.java)

        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser)
    }

    @GetMapping("/users")
    fun getUsers(): ResponseEntity<List<ResponseUser>> {
        val userList: Iterable<UserEntity> = userService.getUserByAll()
        val result = mutableListOf<ResponseUser>()
        userList.forEach {
            result.add(ModelMapper().map(it, ResponseUser::class.java))
        }

        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    @GetMapping("/users/{userId}")
    fun getUsers(@PathVariable("userId") userId: String): ResponseEntity<ResponseUser> {
        val user: UserDto = userService.getUserByUserId(userId)
        val returnValue: ResponseUser = ModelMapper().map(user, ResponseUser::class.java)
        return ResponseEntity.status(HttpStatus.OK).body(returnValue)
    }
}
