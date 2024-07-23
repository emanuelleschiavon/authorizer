package com.caju.domain

import com.caju.domain.ProductType.*
import com.caju.infra.resources.Merchant
import com.caju.infra.resources.TransactionEntity
import com.caju.service.MerchantService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class BalanceMatcherTest {

    @InjectMockKs
    private lateinit var balanceMatcher: BalanceMatcher

    @MockK
    private lateinit var productTypeProperties: ProductTypeProperties

    @MockK
    private lateinit var merchantService: MerchantService

    private val productTypes = mapOf(
        MEAL to listOf(5811, 5812),
        FOOD to listOf(5411, 5412),
        CASH to emptyList()
    )

    @Test
    fun `effects food transaction when merchant is known`() {
        val transactionEntity = TransactionEntity(123, 5050, 200.0, 54, "UBER TRIP                   SAO PAULO BR")
        val account = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 100.0))
        val accountUpdated = Account(5050, mapOf(FOOD to 300.0, MEAL to 50.0, CASH to 100.0))
        every { merchantService.getByName("UBER TRIP                   SAO PAULO BR") } returns Merchant(1, "UBER TRIP                   SAO PAULO BR", 5411)
        every { productTypeProperties.enumMapping } returns productTypes

        val result = balanceMatcher.updateBalance(account, transactionEntity)

        assertEquals(accountUpdated, result)
    }

    @Test
    fun `effects food transaction when mcc is 5411`() {
        val transactionEntity = TransactionEntity(123, 5050, 200.0, 5411, "Supermercados ABC")
        val account = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 100.0))
        val accountUpdated = Account(5050, mapOf(FOOD to 300.0, MEAL to 50.0, CASH to 100.0))
        every { productTypeProperties.enumMapping } returns productTypes
        every { merchantService.getByName("Supermercados ABC") } returns null

        val result = balanceMatcher.updateBalance(account, transactionEntity)

        assertEquals(accountUpdated, result)
    }

    @Test
    fun `effects food transaction when mcc is 5412`() {
        val transactionEntity = TransactionEntity(123, 5050, 200.0, 5412, "Supermercados ABC")
        val account = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 100.0))
        val accountUpdated = Account(5050, mapOf(FOOD to 300.0, MEAL to 50.0, CASH to 100.0))
        every { productTypeProperties.enumMapping } returns productTypes
        every { merchantService.getByName("Supermercados ABC") } returns null

        val result = balanceMatcher.updateBalance(account, transactionEntity)

        assertEquals(accountUpdated, result)
    }

    @Test
    fun `effects meal transaction when mcc is 5811`() {
        val transactionEntity = TransactionEntity(123, 5050, 20.0, 5811, "Supermercados ABC")
        val account = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 100.0))
        val accountUpdated = Account(5050, mapOf(FOOD to 500.0, MEAL to 30.0, CASH to 100.0))
        every { productTypeProperties.enumMapping } returns productTypes
        every { merchantService.getByName("Supermercados ABC") } returns null

        val result = balanceMatcher.updateBalance(account, transactionEntity)

        assertEquals(accountUpdated, result)
    }

    @Test
    fun `effects meal transaction when mcc is 5812`() {
        val transactionEntity = TransactionEntity(123, 5050, 20.0, 5812, "Supermercados ABC")
        val account = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 100.0))
        val accountUpdated = Account(5050, mapOf(FOOD to 500.0, MEAL to 30.0, CASH to 100.0))
        every { productTypeProperties.enumMapping } returns productTypes
        every { merchantService.getByName("Supermercados ABC") } returns null

        val result = balanceMatcher.updateBalance(account, transactionEntity)

        assertEquals(accountUpdated, result)
    }

    @Test
    fun `effects cash transaction when mcc is unknown`() {
        val transactionEntity = TransactionEntity(123, 5050, 20.0, 20, "Supermercados ABC")
        val account = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 100.0))
        val accountUpdated = Account(5050, mapOf(FOOD to 500.0, MEAL to 50.0, CASH to 80.0))
        every { productTypeProperties.enumMapping } returns productTypes
        every { merchantService.getByName("Supermercados ABC") } returns null

        val result = balanceMatcher.updateBalance(account, transactionEntity)

        assertEquals(accountUpdated, result)
    }
}