@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.orderservice.domain

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.io.Serializable
import java.util.*

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false, length = 120)
    var productId: String? = null,
    @Column(nullable = false)
    var qty: Int? = null,
    @Column(nullable = false)
    var unitPrice: Int? = null,
    @Column(nullable = false)
    var totalPrice: Int? = null,

    @Column(nullable = false)
    var userId: String? = null,
    @Column(nullable = false)
    var orderId: String? = null,

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    var createdAt: Date? = null
) : Serializable
