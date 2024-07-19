package com.caju.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.util.UUID
import kotlin.test.assertEquals

class AccountTest {

    private val account =
        Account(5050, mapOf(ProductType.MEAL to 500.0, ProductType.FOOD to 50.0, ProductType.CASH to 100.0))

    @Test
    fun `updates correct balance successfully`() {
        val accountUpdated = account.updateBalance(200.0, ProductType.MEAL)

        val result = accountUpdated.balances
        assertEquals(
            mapOf(
                ProductType.MEAL to 300.0,
                ProductType.FOOD to 50.0,
                ProductType.CASH to 100.0,
            ), result
        )
    }

    @Test
    fun `updates cash balance when product type choose has insufficient funds`() {
        val accountUpdated = account.updateBalance(100.0, ProductType.FOOD)

        val result = accountUpdated.balances
        assertEquals(
            mapOf(
                ProductType.MEAL to 500.0,
                ProductType.FOOD to 50.0,
                ProductType.CASH to 0.0,
            ),
            result
        )
    }

    @Test
    fun `throws exception when balance is less than transaction amount`() {
        assertThrows<RuntimeException> { account.updateBalance(200.0, ProductType.FOOD) }
    }
}