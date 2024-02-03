package com.example.orderservice.infra.messagequeue.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class KafkaOrderDto(
    val schema: Schema,
    val payload: Payload
)