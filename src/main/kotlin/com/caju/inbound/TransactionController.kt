package com.caju.inbound

import com.caju.domain.InsufficientFundsException
import com.caju.service.TransactionService
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.logging.Logger
import kotlin.math.log

@RestController
class TransactionController(
    private val transactionService: TransactionService,
) {

    @PostMapping("/transactions")
    fun create(@RequestBody request: TransactionRequest) =
        try {HttpStatus.OK
            transactionService.authorizer(request.toDomain())
            ResponseEntity(TransactionResponse("00"), HttpStatus.OK)
        } catch (e: Exception) {
            handleError(e)
        }

    private fun handleError(e: Exception) =
        when (e) {
            is InsufficientFundsException -> ResponseEntity(TransactionResponse("51"), HttpStatus.OK)
            else -> {
                val logger = LoggerFactory.getLogger(TransactionController::class.java)
                logger.error(e.localizedMessage)
                ResponseEntity(TransactionResponse("07"), HttpStatus.OK)
            }
        }
}