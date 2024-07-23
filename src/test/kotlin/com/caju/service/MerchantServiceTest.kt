package com.caju.service

import com.caju.infra.MerchantRepository
import com.caju.infra.resources.Merchant
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(MockKExtension::class)
class MerchantServiceTest {

    @InjectMockKs
    private lateinit var merchantService: MerchantService

    @MockK
    private lateinit var merchantRepository: MerchantRepository

    @Test
    fun `saves merchant`() {
        val merchant = Merchant(1L, "Uber", 5411)
        every { merchantRepository.save(merchant) } returns merchant

        val result = merchantService.save(merchant)

        assertEquals(merchant, result)
    }
    @Test
    fun `gets merchant by name`() {
        val merchant = Merchant(1L, "Uber", 5411)
        every { merchantRepository.getByName(merchant.name) } returns merchant

        val result = merchantService.getByName(merchant.name)

        assertEquals(merchant, result)
    }
}