package com.example.firstservice

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.http.HttpServletRequest
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/first-service")
class HelloController(val env: Environment) {
    val logger = KotlinLogging.logger {}

    @GetMapping("/welcome")
    fun welcome(): String = "Welcome to the First service."

    @GetMapping("/message")
    fun message(
        @RequestHeader("first-request") header: String,
    ): String {
        logger.info { header }
        return "Hello message"
    }

    @GetMapping("/check")
    fun check(request: HttpServletRequest): String {
        logger.info{request.serverPort}
        logger.info{env.getProperty("local.server.port")}
        return "Hi check"
    }
}
