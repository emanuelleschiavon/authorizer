package com.caju.domain

import org.springframework.boot.context.properties.ConfigurationProperties

enum class ProductType {
    FOOD, MEAL, CASH;
}

@ConfigurationProperties(prefix = "app.product-type")
class ProductTypeProperties {
    lateinit var enumMapping: Map<ProductType, List<Int>>
}
