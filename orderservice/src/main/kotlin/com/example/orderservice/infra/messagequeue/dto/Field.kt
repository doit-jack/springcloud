package com.example.orderservice.infra.messagequeue.dto

data class Field(
    val type: String,
    val optional: Boolean,
    val field: String
)