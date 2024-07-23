package com.caju.inbound

import com.caju.builders.AccountEntityBuilder
import com.caju.builders.MerchantBuilder
import com.caju.builders.TransactionEntityBuilder
import com.caju.builders.TransactionRequestBuilder
import com.caju.infra.AccountRepository
import com.caju.infra.MerchantRepository
import com.caju.infra.TransactionRepository
import com.google.gson.Gson
import org.flywaydb.core.Flyway
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.assertEquals

@SpringBootTest
@AutoConfigureMockMvc
class AuthorizeTransactionIntegrationTest : TestContainerConfiguration() {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var transactionRepository: TransactionRepository

    @Autowired
    private lateinit var accountRepository: AccountRepository

    @Autowired
    private lateinit var merchantRepository: MerchantRepository

    @BeforeEach
    fun cleanUp() {
        transactionRepository.deleteAll()
        accountRepository.deleteAll()
        merchantRepository.deleteAll()
    }

    @Test
    fun authorizeTransactionByMCC() {
        val request = TransactionRequestBuilder().build()
        val transaction = TransactionEntityBuilder().build()
        val account = AccountEntityBuilder().apply { this.id = transaction.accountId }.build()
        accountRepository.save(account)

        mockMvc.perform(
            post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(Gson().toJson(request))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("00"))
        val result = transactionRepository.findAll().first()

        assertEquals(transaction.accountId, result.accountId)
    }

    @Test
    fun authorizeTransactionByMerchantName() {
        val merchant = MerchantBuilder().build()
        val request = TransactionRequestBuilder().apply {
            this.merchant = merchant.name
            this.mcc = merchant.mcc.toString()
        }.build()
        val transaction = TransactionEntityBuilder().apply {
            this.merchant = merchant.name
            this.mcc = merchant.mcc
        }.build()
        val account = AccountEntityBuilder().apply { this.id = transaction.accountId }.build()
        accountRepository.save(account)
        merchantRepository.save(merchant)

        mockMvc.perform(
            post("/transactions")
                .contentType(APPLICATION_JSON)
                .content(Gson().toJson(request))
        )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.code").value("00"))
        val result = transactionRepository.findAll().first()

        assertEquals(transaction.accountId, result.accountId)
    }
}
