package com.example.gateway.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoggingFilter : AbstractGatewayFilterFactory<LoggingFilter.Config>(Config::class.java) {
    val log = KotlinLogging.logger {}

    class Config(val baseMessage: String, val preLogger: Boolean, val postLogger: Boolean)

    override fun apply(config: Config): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response
            log.info { "Logging Filter baseMessage: ${config.baseMessage}" }

            if (config.preLogger) {
                log.info { "Logging Filter Start: request id -> ${request.id}" }
            }
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    if (config.postLogger) {
                        log.info { "Logging Filter End: request code -> ${response.statusCode}" }
                    }
                },
            )
        }
}
