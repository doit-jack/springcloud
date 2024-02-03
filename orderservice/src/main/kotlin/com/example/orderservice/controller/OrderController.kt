package com.example.orderservice.controller

import com.example.core.utils.log
import com.example.orderservice.application.OrderService
import com.example.orderservice.controller.dto.RequestOrder
import com.example.orderservice.domain.OrderEntity
import com.example.orderservice.domain.dto.OrderDto
import com.example.orderservice.domain.vo.ResponseOrder
import com.example.orderservice.infra.messagequeue.KafkaProducer
import com.example.orderservice.infra.messagequeue.OrderProducer
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration.AccessLevel
import org.modelmapper.convention.MatchingStrategies
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
class OrderController(
    val orderService: OrderService,
    val kafkaProducer: KafkaProducer,
    val orderProducer: OrderProducer
) {
    @PostMapping("/{userId}/orders")
    fun createOrder(
        @PathVariable("userId") userId: String,
        @RequestBody orderRequest: RequestOrder,
    ): ResponseEntity<ResponseOrder> {
        log().info("Before add orders data")
        val mapper = ModelMapper()
        mapper.apply {
            configuration.isFieldMatchingEnabled = true
            configuration.setMatchingStrategy(MatchingStrategies.STRICT)
            configuration.fieldAccessLevel = AccessLevel.PRIVATE
        }
        val dto = mapper.map(orderRequest, OrderDto::class.java)
        dto.userId = userId

        val createOrder = orderService.createOrder(dto)
        val responseOrder = mapper.map(createOrder, ResponseOrder::class.java)

        /* kafka */
        dto.orderId = UUID.randomUUID().toString()
        dto.totalPrice = orderRequest.unitPrice.let { dto.qty?.times(it) }

//        kafkaProducer.send("example-catalog-topic", dto)
//        orderProducer.send("orders", dto)

//        val responseOrder = mapper.map(dto, ResponseOrder::class.java)
        log().info("After added orders data")
        return ResponseEntity.status(HttpStatus.CREATED).body(responseOrder)
    }

    @GetMapping("/{userId}/orders")
    fun getOrder(@PathVariable("userId") userId: String): ResponseEntity<List<ResponseOrder>> {
        log().info("Before retrieve orders data")
        val orderList: Iterable<OrderEntity> = orderService.getOrdersByUserId(userId)
        val result = mutableListOf<ResponseOrder>()
        orderList.forEach {
            result.add(ModelMapper().map(it, ResponseOrder::class.java))
        }
        log().info("Add retrieved orders data")
        return ResponseEntity.status(HttpStatus.OK).body(result)
    }
}