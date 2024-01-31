package com.example.userservice.infra.client.decoder

import feign.Response
import feign.codec.ErrorDecoder
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class FeignErrorDecoder : ErrorDecoder {
    override fun decode(methodKey: String, response: Response): Exception =
        when (response.status()) {
            404 -> ResponseStatusException(
                HttpStatus.valueOf(response.status()), "User's order is empty."
            )

            else -> Exception(response.reason())
        }
}