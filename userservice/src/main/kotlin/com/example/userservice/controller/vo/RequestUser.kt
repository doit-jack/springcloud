package com.example.userservice.controller.vo

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

data class RequestUser(
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    val email: String,
    @Size(min = 2, message = "Name not be less than two characters")
    val name: String,
    @Size(min = 8, message = "Password must be more than 8 characters")
    val pwd: String,
)
