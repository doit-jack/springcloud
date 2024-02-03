package com.example.userservice.controller

import com.example.userservice.application.UserService
import com.example.userservice.controller.dto.UserDto
import com.example.userservice.controller.vo.RequestUser
import com.example.userservice.controller.vo.ResponseUser
import com.example.userservice.domain.UserEntity
import io.micrometer.core.annotation.Timed
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController(
    val userService: UserService,
    @Value("\${greeting.message}") val msg: String,
    val env: Environment,
) {

    @GetMapping("/health-check")
    @Timed(value="users.status", longTask = true)
    fun status(): String = "It's Working, ${env.getProperty("token.secret")}"

    @GetMapping("/welcome")
    @Timed(value="users.welcome", longTask = true)
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
    fun getUser(@PathVariable("userId") userId: String): ResponseEntity<ResponseUser> {
        val user: UserDto = userService.getUserByUserId(userId)
        val returnValue: ResponseUser = ModelMapper().map(user, ResponseUser::class.java)
        return ResponseEntity.status(HttpStatus.OK).body(returnValue)
    }
}
