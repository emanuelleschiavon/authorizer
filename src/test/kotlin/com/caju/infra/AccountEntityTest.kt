package com.caju.infra

import com.caju.domain.Account
import com.caju.domain.ProductType
import com.caju.infra.resources.AccountEntity
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


class AccountEntityTest {

    @Test
    fun `parses entity to domain`() {
        val account = Account(
            1L, mapOf(
                ProductType.FOOD to 100.0,
                ProductType.MEAL to 100.0,
                ProductType.CASH to 100.0,
            )
        )
        val accountEntity = AccountEntity(1L, 100.0, 100.0, 100.0)

        val result = AccountEntity(account)

        assertEquals(accountEntity, result)
    }

    @Test
    fun `parses domain to entity`() {
        val account = Account(
            1L, mapOf(
                ProductType.FOOD to 100.0,
                ProductType.MEAL to 100.0,
                ProductType.CASH to 100.0,
            )
        )
        val accountEntity = AccountEntity(1L, 100.0, 100.0, 100.0)

        val result = accountEntity.toDomain()

        assertEquals(account, result)
    }
}