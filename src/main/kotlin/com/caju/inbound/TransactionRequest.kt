package com.caju.inbound

import com.caju.domain.TransactionEntity

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
