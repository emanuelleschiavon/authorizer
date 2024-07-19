package com.caju.domain

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

enum class ProductType(val mccs: List<Int>) {
    FOOD(listOf(5411, 5412)), MEAL(listOf(5811, 5812)), CASH(emptyList());

    companion object {
        fun getProductByMcc(mcc: Int): ProductType =
            entries.firstOrNull { it.mccs.contains(mcc) } ?: CASH
    }
}

@Component
class ProductTypeProperties{
    @Value("\${app.product-type.food}")
    lateinit var food: List<String>
    @Value("\${app.product-type.meal}")
    lateinit var meal: List<String>
    @Value("\${app.product-type.cash}")
    lateinit var cash: List<String>
}
