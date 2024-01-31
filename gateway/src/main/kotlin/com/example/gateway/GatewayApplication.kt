package com.example.gateway

import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository
import org.springframework.boot.actuate.web.exchanges.InMemoryHttpExchangeRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication(scanBasePackages = ["com.example"])
class GatewayApplication {
    @Bean
    fun httpTraceRepository(): HttpExchangeRepository = InMemoryHttpExchangeRepository()
}

fun main(args: Array<String>) {
    runApplication<GatewayApplication>(*args)
}


