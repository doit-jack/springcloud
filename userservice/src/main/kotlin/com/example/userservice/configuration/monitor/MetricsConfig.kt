package com.example.userservice.configuration.monitor

import io.micrometer.core.aop.TimedAspect
import io.micrometer.core.instrument.MeterRegistry
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy

@Configuration
@EnableAspectJAutoProxy
class MetricsConfig {
    @Bean
    fun timedAspect(registry: MeterRegistry?): TimedAspect {
        return TimedAspect(registry!!)
    }
}