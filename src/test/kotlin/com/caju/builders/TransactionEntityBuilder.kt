package com.caju.builders

import com.caju.infra.resources.TransactionEntity

class TransactionEntityBuilder {
    var id: Long = 1L
    var accountId: Long = 123L
    var amount: Double = 20.0
    var mcc: Int = 5411
    var merchant: String = "PADARIA DO ZE               SAO PAULO BR"

    fun build() = TransactionEntity(id, accountId, amount, mcc, merchant)
}