package com.example.catalogservice.domain.vo

import com.fasterxml.jackson.annotation.JsonInclude
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseCatalog(
    var productId: String? = null,
    var productName: String? = null,
    var unitPrice: Int? = null,
    var stock: Int? = null,
    var createAt: Date? = null,
)