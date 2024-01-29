package com.example.catalogservice.domain

import java.io.Serializable

data class CatalogDto(
    var productId: String? = null,
    var qty: Int? = null,
    var unitPrice: Int? = null,
    var totalPrice: Int? = null,
    var orderId: String? = null,
    var userId: String? = null,
) : Serializable