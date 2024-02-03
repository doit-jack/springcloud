package com.example.userservice.configuration.resilience4j

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.timelimiter.TimeLimiterConfig
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder
import org.springframework.cloud.client.circuitbreaker.Customizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class Resilience4JConfig {

    @Bean
    fun customR4jConfig(): Customizer<Resilience4JCircuitBreakerFactory> {
        val circuitBreakerConf = CircuitBreakerConfig.custom().failureRateThreshold(4F)
            .waitDurationInOpenState(Duration.ofMillis(1000))
            .slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
            .slidingWindowSize(2).build()
        val timeLimiterConf =
            TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(4000)).build()

        return Customizer { factory ->
            factory.configureDefault {
                Resilience4JConfigBuilder(it)
                    .timeLimiterConfig(timeLimiterConf)
                    .circuitBreakerConfig(circuitBreakerConf)
                    .build()
            }
        }



    }

}