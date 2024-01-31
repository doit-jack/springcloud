package com.example.catalogservice.repository

import com.example.catalogservice.domain.CatalogEntity
import org.springframework.data.repository.CrudRepository

interface CatalogRepository : CrudRepository<CatalogEntity, Long> {
    fun findByProductId(productId: String): CatalogEntity
}