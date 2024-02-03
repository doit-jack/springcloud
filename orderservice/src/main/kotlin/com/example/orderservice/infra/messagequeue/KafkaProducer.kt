package com.example.orderservice.infra.messagequeue

import com.example.core.utils.log
import com.example.orderservice.domain.dto.OrderDto
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaProducer(val kafkaTemplate: KafkaTemplate<String, String>) {
    fun send(topic: String, orderDto: OrderDto): OrderDto {
        val mapper = ObjectMapper()
        var jsonInString = ""
        try {
            jsonInString = mapper.writeValueAsString(orderDto)
        } catch (ex: JsonProcessingException) {
            ex.printStackTrace()
        }

        kafkaTemplate.send(topic, jsonInString)
        log().info("Kafka Producer sent data from the Order microservice: $orderDto")
        return orderDto
    }

}