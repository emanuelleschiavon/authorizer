package com.caju.service

import com.caju.domain.TransactionEntity
import com.caju.infra.AccountEntity
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

    @Test
    fun `process transaction successfully`() {
        val transactionEntity = TransactionEntity(1L, 2L, 200.0, 5411, "Mercados AB")
        val account = AccountEntity(transactionEntity.accountId, 200.0, 200.0, 200.0)
        val accountUpdate = AccountEntity(transactionEntity.accountId, 0.0, 200.0, 200.0)
        every { accountRepository.findByIdOrNull(transactionEntity.accountId) } returns account
        every { accountRepository.save(accountUpdate) } returns accountUpdate
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