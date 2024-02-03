package com.example.userservice.controller.vo

import com.fasterxml.jackson.annotation.JsonInclude


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseUser(
    var email: String = "",
    var name: String = "",
    var userId: String = "",
    var orders: List<ResponseOrder> = mutableListOf()
)
