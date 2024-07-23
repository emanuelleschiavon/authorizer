package com.caju.service

import com.caju.domain.Account
import com.caju.domain.BalanceMatcher
import com.caju.domain.ProductType.*
import com.caju.infra.resources.TransactionEntity
import com.caju.infra.resources.AccountEntity
import com.caju.infra.AccountRepository
import com.caju.infra.TransactionRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.data.repository.findByIdOrNull
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class TransactionServiceTest {

    @InjectMockKs
    private lateinit var transactionService: TransactionService

    @MockK
    private lateinit var transactionRepository: TransactionRepository

    @MockK
    private lateinit var accountRepository: AccountRepository

    @MockK
    private lateinit var balanceMatcher: BalanceMatcher

    @Test
    fun `process transaction successfully`() {
        val transactionEntity = TransactionEntity(1L, 2L, 200.0, 5411, "Mercados AB")
        val accountEntity = AccountEntity(transactionEntity.accountId, 200.0, 200.0, 200.0)
        val account = Account(transactionEntity.accountId, mapOf(FOOD to 200.0, MEAL to 200.0, CASH to 200.0))
        val accountEntityUpdated = AccountEntity(transactionEntity.accountId, 0.0, 200.0, 200.0)
        val accountUpdated = Account(transactionEntity.accountId, mapOf(FOOD to 0.0, MEAL to 200.0, CASH to 200.0))
        every { accountRepository.findByIdOrNull(transactionEntity.accountId) } returns accountEntity
        every { balanceMatcher.updateBalance(account, transactionEntity) } returns accountUpdated
        every { accountRepository.save(accountEntityUpdated) } returns accountEntityUpdated
        every { transactionRepository.save(transactionEntity) } returns transactionEntity

        val result = transactionService.authorizer(transactionEntity)

        assertEquals(transactionEntity, result)
    }

    @Test
    fun `throws exception when account does not exist`() {
        val transactionEntity = TransactionEntity(1L, 2L, 200.0, 5411, "Mercados AB")
        every { accountRepository.findByIdOrNull(transactionEntity.accountId) } returns null

        assertThrows<RuntimeException> { transactionService.authorizer(transactionEntity) }
    }
}