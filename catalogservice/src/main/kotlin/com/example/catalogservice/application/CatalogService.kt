package com.example.catalogservice.application

import com.example.catalogservice.domain.CatalogEntity

interface CatalogService {
    fun getAllCatalogs(): Iterable<CatalogEntity>
}