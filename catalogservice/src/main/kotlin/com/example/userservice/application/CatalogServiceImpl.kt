package com.example.catalogservice.application

import com.example.catalogservice.domain.CatalogEntity
import com.example.catalogservice.repository.CatalogRepository
import org.springframework.stereotype.Service

@Service
class CatalogServiceImpl(
    val catalogRepository: CatalogRepository
) : CatalogService {
    override fun getAllCatalogs(): MutableIterable<CatalogEntity> = catalogRepository.findAll()

}