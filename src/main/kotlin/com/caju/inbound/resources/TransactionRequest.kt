package com.caju.inbound.resources

import com.caju.infra.resources.TransactionEntity

data class TransactionRequest(
    val account: String,
    val totalAmount: Double,
    val mcc: String,
    val merchant: String,
) {
    fun toDomain() = TransactionEntity(
        accountId = account.toLong(),
        amount = totalAmount,
        merchant = merchant,
        mcc = mcc.toInt(),
    )
}
