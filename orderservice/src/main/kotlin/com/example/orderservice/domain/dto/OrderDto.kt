package com.example.orderservice.domain.dto

import java.io.Serializable

data class OrderDto(
    var productId: String? = null,
    var qty: Int? = null,
    var unitPrice: Int? = null,
    var totalPrice: Int? = null,
    var orderId: String? = null,
    var userId: String? = null,
) : Serializable