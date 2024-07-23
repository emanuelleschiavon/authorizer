package com.caju.domain

import com.caju.infra.resources.TransactionEntity
import com.caju.service.MerchantService
import org.springframework.stereotype.Component

@Component
class BalanceMatcher(
    private val productTypeProperties: ProductTypeProperties,
    private val merchantService: MerchantService,
) {
    fun updateBalance(account: Account, transactionEntity: TransactionEntity): Account =
        getProductTypeByMerchant(transactionEntity.merchant)
            ?.let { account.updateBalance(transactionEntity.amount, it) }
        ?: getProductTypeByMcc(transactionEntity.mcc)
            ?.let { account.updateBalance(transactionEntity.amount, it) }
        ?: ProductType.CASH
            .let { account.updateBalance(transactionEntity.amount, it) }

    private fun getProductTypeByMcc(mcc: Int): ProductType? =
        productTypeProperties.enumMapping.entries
            .firstOrNull { it.value.contains(mcc) }?.key

    private fun getProductTypeByMerchant(merchant: String): ProductType? =
        merchantService.getByName(merchant)?.mcc
            ?.let { getProductTypeByMcc(it) }
}
