package com.example.orderservice.domain.vo

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseOrder(
    var productId: String? = null,
    var qty: Int? = null,
    var unitPrice: Int? = null,
    var totalPrice: Int? = null,
    var createAt: Date? = null,
    var orderId: String? = null,
)