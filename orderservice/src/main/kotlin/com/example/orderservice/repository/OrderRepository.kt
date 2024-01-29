package com.example.orderservice.repository

import com.example.orderservice.domain.OrderEntity
import org.springframework.data.repository.CrudRepository

interface OrderRepository : CrudRepository<OrderEntity, Long> {
    fun findByOrderId(orderId: String): OrderEntity

    fun findByUserId(userId: String): Iterable<OrderEntity>
}