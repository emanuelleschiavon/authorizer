package com.caju.builders

import com.caju.infra.resources.Merchant

class MerchantBuilder {
    var id: Long = 1L
    var name: String = "UBER EATS                   SAO PAULO BR"
    var mcc: Int = 5411

    fun build() = Merchant(id, name, mcc)
}