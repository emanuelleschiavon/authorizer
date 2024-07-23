package com.caju.builders

import com.caju.inbound.resources.TransactionRequest

class TransactionRequestBuilder {
    var accountId: String = "123"
    var amount: Double = 20.0
    var mcc: String = "5411"
    var merchant: String = "PADARIA DO ZE               SAO PAULO BR"

    fun build() = TransactionRequest(accountId, amount, mcc, merchant)
}