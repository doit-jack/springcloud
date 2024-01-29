package com.example.discoveryservice

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HelloController {
    @GetMapping("/welcome")
    fun welcome(): String = "Welcome to the Second service."
}
