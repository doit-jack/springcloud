package com.example.userservice.configuration.client

import feign.Logger
import org.springframework.cloud.client.loadbalancer.LoadBalanced
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate


/**
 * Feign-Client 사용으로 미사용
 */
@Configuration
class RestConfig {
    @Bean
    @LoadBalanced // Eureka Server에 eureka-client로 등록된 모든 클라이언트에 대해 Load Balancing 수행
    fun getRestTemplate() = RestTemplate()

    @Bean
    fun feignLoggerLevel() = Logger.Level.FULL
}