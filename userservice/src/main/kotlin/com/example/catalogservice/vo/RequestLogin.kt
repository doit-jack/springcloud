package com.example.userservice.vo

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class RequestLogin(
    @NotNull(message = "Email cannot be null")
    @Size(min = 2, message = "Email not be less than two characters")
    @Email
    var email: String? = null,
    @NotNull(message = "Password cannot be null")
    @Size(min = 8, message = "Password must be equals or grater than two characters")
    var password: String? = null
)