package com.example.userservice.controller.dto

import com.example.userservice.controller.vo.ResponseOrder
import java.util.Date

data class UserDto(
    var email: String = "",
    var name: String = "",
    var pwd: String = "",
    var userId: String = "",
    var createdAt: Date = Date(),
    var encryptedPwd: String = "",
    var orders: List<ResponseOrder> = listOf()
)
