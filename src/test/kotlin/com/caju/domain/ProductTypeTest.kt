package com.caju.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID
import kotlin.test.assertEquals

class ProductTypeTest {

    @Test
    fun `returns FOOD when mcc is 5411`() {
        val result = ProductType.getProductByMcc(5411)

        assertEquals(ProductType.FOOD, result)
    }

    @Test
    fun `returns FOOD when mcc is 5412`() {
        val result = ProductType.getProductByMcc(5412)

        assertEquals(ProductType.FOOD, result)
    }

    @Test
    fun `returns FOOD when mcc is 5811`() {
        val result = ProductType.getProductByMcc(5811)

        assertEquals(ProductType.MEAL, result)
    }

    @Test
    fun `returns FOOD when mcc is 5812`() {
        val result = ProductType.getProductByMcc(5812)

        assertEquals(ProductType.MEAL, result)
    }

    @Test
    fun `returns SL when mcc is unknown`() {
        val result = ProductType.getProductByMcc(2)

        assertEquals(ProductType.CASH, result)
    }
}