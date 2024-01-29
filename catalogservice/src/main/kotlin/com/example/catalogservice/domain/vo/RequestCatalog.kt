package com.example.catalogservice.domain.vo

data class RequestCatalog (
    var productId: String? = null,
    var productName: String? = null,
    var stock: Int? = null,
    var unitPrice: Int? = null,
)

