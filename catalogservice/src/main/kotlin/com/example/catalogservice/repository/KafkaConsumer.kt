package com.example.catalogservice.repository

import com.example.core.utils.log
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service


@Service
class KafkaConsumer(val catalogRepository: CatalogRepository) {

    @KafkaListener(topics = ["example-catalog-topic"])
    fun updateQty(kafkaMessage: String) {
        log().info("Kafka Message: -> $kafkaMessage")
        val mapper = ObjectMapper()

        var map = mapOf<Any, Any>()
        try {
            map = mapper.readValue(kafkaMessage, object : TypeReference<Map<Any, Any>>() {})
        } catch (ex: JsonProcessingException) {
            ex.printStackTrace()
        }

        val catalog = catalogRepository.findByProductId(map["productId"].toString())
            ?: throw RuntimeException()
        map["qty"]?.toString()?.toInt()?.let {
            catalog.stock = catalog.stock?.minus(it)
        }
        catalogRepository.save(catalog)
    }

}