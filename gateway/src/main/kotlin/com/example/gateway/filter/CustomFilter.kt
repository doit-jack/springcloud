package com.example.gateway.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter : AbstractGatewayFilterFactory<Any>() {
    val logger = KotlinLogging.logger {}

    override fun apply(config: Any): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response
            logger.info { "Custom PRE request Id: " + request.id }
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    logger.info { "Custom POST filter: response code -> ${response.statusCode}" }
                },
            )
        }
}
