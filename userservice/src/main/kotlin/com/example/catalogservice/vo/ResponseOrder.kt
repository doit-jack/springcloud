package com.example.userservice.vo

import java.util.*


data class ResponseOrder(
    val productId: String = "",
    val qty:Int = 0,
    val unitPrice: Int = 0,
    val totalPrice: Int = 0,
    val createdAt: Date = Date(),
    val orderId: String = ""
)
