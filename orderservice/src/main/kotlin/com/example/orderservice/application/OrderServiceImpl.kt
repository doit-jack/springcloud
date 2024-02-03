package com.example.orderservice.application

import com.example.orderservice.domain.OrderEntity
import com.example.orderservice.domain.dto.OrderDto
import com.example.orderservice.infra.repository.OrderRepository
import org.modelmapper.ModelMapper
import org.modelmapper.convention.MatchingStrategies
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderServiceImpl(
    val orderRepository: OrderRepository
) : OrderService {
    override fun createOrder(orderDto: OrderDto): OrderDto {
        orderDto.orderId = UUID.randomUUID().toString()
        orderDto.totalPrice = orderDto.unitPrice?.let { orderDto.qty?.times(it) }
        // TODO: ModelMapper를 쓰자니, 모두 nullable로 해야되서 이상한 비즈니스 코드가 된다. 개선해보자.
        val mapper = ModelMapper()
        mapper.configuration.setMatchingStrategy(MatchingStrategies.STRICT)
        val orderEntity: OrderEntity = mapper.map(orderDto, OrderEntity::class.java)

        orderRepository.save(orderEntity)
        return orderDto
    }

    override fun getOrderByOrderId(orderId: String): OrderDto {
        val orderEntity = orderRepository.findByOrderId(orderId)
        return ModelMapper().map(orderEntity, OrderDto::class.java)
    }

    override fun getOrdersByUserId(userId: String) = orderRepository.findByUserId(userId)
}