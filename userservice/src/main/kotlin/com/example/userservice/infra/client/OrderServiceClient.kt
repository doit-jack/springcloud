package com.example.userservice.infra.client

import com.example.userservice.controller.vo.ResponseOrder
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name = "order-service")
interface OrderServiceClient {
    @GetMapping("/{userId}/orders")
    fun getOrders(@PathVariable userId: String): List<ResponseOrder>

    @GetMapping("/{userId}/orders/ng")
    fun getOrdersNg(@PathVariable userId: String): List<ResponseOrder>

}