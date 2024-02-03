package com.example.orderservice.infra.messagequeue

import com.example.core.utils.log
import com.example.orderservice.domain.dto.OrderDto
import com.example.orderservice.infra.messagequeue.dto.Field
import com.example.orderservice.infra.messagequeue.dto.KafkaOrderDto
import com.example.orderservice.infra.messagequeue.dto.Payload
import com.example.orderservice.infra.messagequeue.dto.Schema
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class OrderProducer(val kafkaTemplate: KafkaTemplate<String, String>) {

    val fields: List<Field> = listOf(
        Field("string", true, "order_id"),
        Field("string", true, "product_id"),
        Field("string", true, "user_id"),
        Field("int64", true, "qty"),
        Field("int64", true, "unit_price"),
        Field("int64", true, "total_price"),
    )

    val schema: Schema = Schema.Builder()
        .type("struct").fields(fields).optional(false).name("orders").build()

    fun send(topic: String, orderDto: OrderDto): OrderDto {
        val payload = Payload.Builder().orderId(orderDto.orderId!!).userId(orderDto.userId!!)
            .productId(orderDto.productId!!).qty(orderDto.qty!!).unitPrice(orderDto.unitPrice!!)
            .totalPrice(orderDto.totalPrice!!).build()

        val kafkaOrderDto = KafkaOrderDto(schema, payload)

        val mapper = ObjectMapper()
        var jsonInString = ""
        try {
            jsonInString = mapper.writeValueAsString(kafkaOrderDto)
        } catch (ex: JsonProcessingException) {
            ex.printStackTrace()
        }

        kafkaTemplate.send(topic, jsonInString)
        log().info("Order Producer sent data from the Order microservice: $kafkaOrderDto")
        return orderDto
    }

}