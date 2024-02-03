package com.example.orderservice.infra.messagequeue.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Payload(
    val orderId: String,
    val userId: String,
    val productId: String,
    val qty: Int,
    val unitPrice: Int,
    val totalPrice: Int,
) {
    data class Builder(
        var orderId: String = "",
        var userId: String = "",
        var productId: String = "",
        var qty: Int = 0,
        var unitPrice: Int = 0,
        var totalPrice: Int = 0
    ) {
        fun orderId(orderId: String) = apply { this.orderId = orderId }
        fun userId(userId: String) = apply { this.userId = userId }
        fun productId(productId: String) = apply { this.productId = productId }
        fun qty(qty: Int) = apply { this.qty = qty }
        fun unitPrice(unitPrice: Int) = apply { this.unitPrice = unitPrice }
        fun totalPrice(totalPrice: Int) = apply { this.totalPrice = totalPrice }

        fun build() = Payload(orderId, userId, productId, qty, unitPrice, totalPrice)
    }
}

