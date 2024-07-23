package com.caju.infra

import com.caju.infra.resources.Merchant
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface MerchantRepository : CrudRepository<Merchant, Long> {
    fun save(merchant: Merchant): Merchant

    fun getByName(name: String): Merchant?
}
