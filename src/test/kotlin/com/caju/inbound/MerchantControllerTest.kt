package com.caju.inbound

import com.caju.infra.resources.Merchant
import com.caju.service.MerchantService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@ExtendWith(MockKExtension::class)
class MerchantControllerTest {

    @InjectMockKs
    private lateinit var merchantController: MerchantController

    @MockK
    private lateinit var merchantService: MerchantService

    @Test
    fun `returns 201 when merchant is created`() {
        val request = Merchant(1, "Uber", 5811)
        every { merchantService.save(request) } returns request

        val result = merchantController.create(request)

        assertEquals(ResponseEntity(request, HttpStatus.OK), result)
    }
    
    @Test
    fun `returns merchant`() {
        val merchant = Merchant(1, "Uber", 5411)
        every { merchantService.getByName("Uber") } returns merchant

        val result = merchantController.get("Uber")

        assertEquals(ResponseEntity(merchant, HttpStatus.OK), result)
    }
}