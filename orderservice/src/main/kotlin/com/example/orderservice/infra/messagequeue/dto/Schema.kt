package com.example.orderservice.infra.messagequeue.dto

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class Schema(
    val type: String,
    val fields: List<Field>,
    val optional: Boolean,
    val name: String
) {
    data class Builder(
        var type: String = "",
        var fields: List<Field> = emptyList(),
        var optional: Boolean = false,
        var name: String = ""
    ) {
        fun type(type: String) = apply { this.type = type }
        fun fields(fields: List<Field>) = apply { this.fields = fields }
        fun optional(optional: Boolean) = apply { this.optional = optional }
        fun name(name: String) = apply { this.name = name }

        fun build() = Schema(type, fields, optional, name)
    }
}
