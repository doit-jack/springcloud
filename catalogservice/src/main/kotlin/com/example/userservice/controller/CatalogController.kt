package com.example.catalogservice.controller

import com.example.catalogservice.application.CatalogService
import com.example.catalogservice.domain.CatalogEntity
import com.example.catalogservice.domain.vo.RequestCatalog
import com.example.catalogservice.domain.vo.ResponseCatalog
import com.example.catalogservice.repository.CatalogRepository
import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CatalogController(
    val env: Environment,
    val catalogService: CatalogService,
    val catalogRepository: CatalogRepository
) {
    @GetMapping("/catalogs")
    fun getCatalogs(): ResponseEntity<List<ResponseCatalog>> {
        val catalogList: Iterable<CatalogEntity> = catalogService.getAllCatalogs()
        val result = mutableListOf<ResponseCatalog>()
        catalogList.forEach {
            result.add(ModelMapper().map(it, ResponseCatalog::class.java))
        }

        return ResponseEntity.status(HttpStatus.OK).body(result)
    }

    @PostMapping("/catalog")
    fun createCatalog(@RequestBody request: RequestCatalog) {
        val mapper = ModelMapper()
        mapper.apply {
            configuration.isFieldMatchingEnabled = true
            configuration.setMatchingStrategy(MatchingStrategies.STRICT)
            configuration.fieldAccessLevel = Configuration.AccessLevel.PRIVATE
        }
        val catalog = mapper.map(request, CatalogEntity::class.java)
        catalogRepository.save(catalog)
    }
}