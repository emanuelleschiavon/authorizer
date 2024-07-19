package com.caju.domain

class BalanceMatcher(
    private val transactionEntity: TransactionEntity,
    private val account: Account,
) {
    fun updateBalance() =
        ProductType.getProductByMcc(transactionEntity.mcc)
            .let { account.updateBalance(transactionEntity.amount, it) }
}
