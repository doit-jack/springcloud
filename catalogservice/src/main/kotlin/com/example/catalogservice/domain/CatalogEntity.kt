@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.catalogservice.domain

import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.util.*

@Entity
@Table(name = "catalog")
class CatalogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    @Column(nullable = false, length = 120, unique = true)
    var productId: String? = null,
    @Column(nullable = false)
    var productName: String? = null,
    @Column(nullable = false)
    var stock: Int? = null,
    @Column(nullable = false)
    var unitPrice: Int? = null,
    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    var createdAt: Date? = null
)
