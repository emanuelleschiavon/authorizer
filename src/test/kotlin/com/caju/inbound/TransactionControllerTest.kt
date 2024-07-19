package com.caju.inbound

import com.caju.domain.InsufficientFundsException
import com.caju.domain.TransactionEntity
import com.caju.service.TransactionService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@ExtendWith(MockKExtension::class)
class TransactionControllerTest {

    @InjectMockKs
    private lateinit var transactionController: TransactionController

    @MockK
    private lateinit var transactionService: TransactionService

    @Test
    fun `returns code 00 when transaction is authorized`() {
        val request = TransactionRequest("123", 100.00, "5811", "PADARIA DO ZE               SAO PAULO BR")
        val transactionEntity = TransactionEntity(0, 123L, 100.00, 5811, "PADARIA DO ZE               SAO PAULO BR")
        every { transactionService.authorizer(transactionEntity) } returns transactionEntity

        val result = transactionController.create(request)

        assertEquals(
            ResponseEntity(TransactionResponse("00"), HttpStatus.OK),
            result
        )
    }

    @Test
    fun `returns code 51 when accounts has insufficient funds`() {
        val request = TransactionRequest("123", 100.00, "5811", "PADARIA DO ZE               SAO PAULO BR")
        val transactionEntity = TransactionEntity(0, 123L, 100.00, 5811, "PADARIA DO ZE               SAO PAULO BR")
        every { transactionService.authorizer(transactionEntity) } throws InsufficientFundsException()

        val result = transactionController.create(request)

        assertEquals(
            ResponseEntity(TransactionResponse("51"), HttpStatus.OK),
            result
        )
    }

    @Test
    fun `returns code 07 when transaction was not authorized successfully`() {
        val request = TransactionRequest("123", 100.00, "5811", "PADARIA DO ZE               SAO PAULO BR")
        val transactionEntity = TransactionEntity(0, 123L, 100.00, 5811, "PADARIA DO ZE               SAO PAULO BR")
        every { transactionService.authorizer(transactionEntity) } throws RuntimeException()

        val result = transactionController.create(request)

        assertEquals(
            ResponseEntity(TransactionResponse("07"), HttpStatus.OK),
            result
        )
    }
}