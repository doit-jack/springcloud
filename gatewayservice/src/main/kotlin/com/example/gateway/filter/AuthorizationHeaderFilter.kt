package com.example.gateway.filter

import com.example.core.utils.JwtUtils
import com.example.core.utils.log
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders.AUTHORIZATION
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class AuthorizationHeaderFilter(
    val jwtUtils: JwtUtils
) : AbstractGatewayFilterFactory<AuthorizationHeaderFilter.Config>(Config::class.java) {

    class Config

    override fun apply(config: Config): GatewayFilter =
        GatewayFilter { exchange, chain ->
            val request = exchange.request
            if (!request.headers.containsKey(AUTHORIZATION)) {
                return@GatewayFilter onError(
                    exchange, "No Authorization Header!", HttpStatus.UNAUTHORIZED
                )
            }
            val authHeader = request.headers.getOrEmpty(AUTHORIZATION).first()
            val jwt = authHeader.replace("Bearer", "")
            if (!jwtUtils.isJwtValid(jwt)) {
                return@GatewayFilter onError(
                    exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED
                )
            }
            chain.filter(exchange)
        }
// 위와 아래가 같은 코드임.
//    override fun apply(config: Config): GatewayFilter =
//        GatewayFilter { exchange, chain ->
//            val request = exchange.request
//            if (!request.headers.containsKey(AUTHORIZATION)) {
//                onError(exchange, "No Authorization Header!", HttpStatus.UNAUTHORIZED)
//            } else {
//                val authHeader = request.headers.getOrEmpty(AUTHORIZATION).first()
//                val jwt = authHeader.replace("Bearer", "")
//                if (!isJwtValid(jwt)) {
//                    onError(exchange, "JWT token is not valid", HttpStatus.UNAUTHORIZED)
//                } else {
//                    chain.filter(exchange)
//                }
//            }
//        }

    private fun onError(
        exchange: ServerWebExchange,
        errorMessage: String,
        httpStatus: HttpStatus
    ): Mono<Void> {
        val response = exchange.response
        response.statusCode = httpStatus
        log().error(errorMessage)
        return response.setComplete()
    }
}