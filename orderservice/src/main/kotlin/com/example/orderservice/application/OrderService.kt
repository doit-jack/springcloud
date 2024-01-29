package com.example.orderservice.application

import com.example.orderservice.domain.OrderEntity
import com.example.orderservice.domain.dto.OrderDto

interface OrderService {
    fun createOrder(orderDto: OrderDto): OrderDto
    fun getOrderByOrderId(orderId: String): OrderDto
    fun getOrdersByUserId(userId: String): Iterable<OrderEntity>
}