package com.example.orderservice.controller.dto

data class RequestOrder(
    var productId: String,
    var qty: Int,
    var unitPrice: Int
)

