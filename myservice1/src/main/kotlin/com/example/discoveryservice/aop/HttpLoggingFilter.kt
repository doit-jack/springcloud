@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.firstservice.aop

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.util.*

//@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class HttpLoggingFilter : OncePerRequestFilter() {
    private val log = KotlinLogging.logger {}

    companion object {
        const val REQUEST_ID = "request_id"
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val cachingRequestWrapper = ContentCachingRequestWrapper(request)
        val cachingResponseWrapper = ContentCachingResponseWrapper(response)

        log.info { "Jack: Hi" }
        log.info { "Jack: " + cachingRequestWrapper.requestURI }
        val startTime = System.currentTimeMillis()
        filterChain.doFilter(cachingRequestWrapper, cachingResponseWrapper)
        val end = System.currentTimeMillis()

        val requestId = UUID.randomUUID().toString().substring(0, 8)
        cachingResponseWrapper.copyBodyToResponse()

//        MDC.put(REQUEST_ID, requestId)
//
//        try {
//            log.info {
//                HttpLogMessage.createInstance(
//                    requestWrapper = cachingRequestWrapper,
//                    responseWrapper = cachingResponseWrapper,
//                    elapsedTime = (end - startTime) / 1000.0,
//                ).toPrettierLog()
//            }
//            cachingResponseWrapper.copyBodyToResponse()
//        } catch (e: Exception) {
//            log.error(e) { "[${this::class.simpleName}] Logging 실패" }
//        }
//        MDC.remove(REQUEST_ID)
    }
}

data class HttpLogMessage(
    val httpMethod: String,
    val requestUri: String,
    val httpStatus: HttpStatus,
    val clientIp: String,
    val elapsedTime: Double,
    val headers: String?,
    val requestParam: String?,
    val requestBody: String?,
    val responseBody: String?,
) {
    companion object {
        fun createInstance(
            requestWrapper: ContentCachingRequestWrapper,
            responseWrapper: ContentCachingResponseWrapper,
            elapsedTime: Double,
        ): HttpLogMessage {
            return HttpLogMessage(
                httpMethod = requestWrapper.method,
                requestUri = requestWrapper.requestURI,
                httpStatus = HttpStatus.valueOf(responseWrapper.status),
                clientIp = requestWrapper.remoteAddr,
                elapsedTime = elapsedTime,
                headers = requestWrapper.getHeader("HTTP_CLIENT_IP"),
                requestParam = requestWrapper.parameterMap.toString(),
                requestBody = requestWrapper.request.toString(),
                responseBody = responseWrapper.response.toString(),
            )
        }
    }

    // 이부분은 각자 취향대로 포멧 정하는 것으로,,,
    fun toPrettierLog(): String {
        return """
        |
        |[REQUEST] ${this.httpMethod} ${this.requestUri} ${this.httpStatus} (${this.elapsedTime})
        |>> CLIENT_IP: ${this.clientIp}
        |>> HEADERS: ${this.headers}
        |>> REQUEST_PARAM: ${this.requestParam}
        |>> REQUEST_BODY: ${this.requestBody}
        |>> RESPONSE_BODY: ${this.responseBody}
            """.trimMargin()
    }
}
