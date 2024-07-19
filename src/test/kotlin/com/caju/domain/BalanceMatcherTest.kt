package com.caju.domain

import com.caju.domain.ProductType.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BalanceMatcherTest {

    @Test
    fun `effects transaction`() {
        val transactionEntity = TransactionEntity(123, 5050, 200.0, 5411, "Supermercados ABC")
        val account = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 100.0))

        val handler = BalanceMatcher(transactionEntity, account)
        val newAccount = handler.updateBalance()

        assertEquals(300.0, newAccount.balances[FOOD])
    }
}