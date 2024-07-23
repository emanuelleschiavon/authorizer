package com.caju.service

import com.caju.infra.MerchantRepository
import com.caju.infra.resources.Merchant
import org.springframework.stereotype.Service

@Service
class MerchantService(
    private val merchantRepository: MerchantRepository,
) {
    fun save(merchant: Merchant): Merchant =
        merchantRepository.save(merchant)

    fun getByName(name: String): Merchant? =
        merchantRepository.getByName(name)
}
