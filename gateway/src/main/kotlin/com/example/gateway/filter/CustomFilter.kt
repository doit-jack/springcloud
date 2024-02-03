package com.example.gateway.filter

import com.example.core.utils.log
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomFilter : AbstractGatewayFilterFactory<Any>() {

    override fun apply(config: Any): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val request = exchange.request
            val response = exchange.response
            log().info("Custom PRE request Id: " + request.id)
            chain.filter(exchange).then(
                Mono.fromRunnable {
                    log().info("Custom POST filter: response code -> ${response.statusCode}")
                },
            )
        }
}
