package com.example.orderservice.controller

data class RequestOrder(
    var productId: String,
    var qty: Int,
    var unitPrice: Int
)

