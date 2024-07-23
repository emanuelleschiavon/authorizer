package com.caju.builders

import com.caju.infra.resources.AccountEntity

class AccountEntityBuilder {
    var id: Long = 123L
    var balanceFood: Double = 100.0
    var balanceMeal: Double = 100.0
    var balanceCash: Double = 100.0

    fun build() = AccountEntity(id, balanceFood, balanceMeal, balanceCash)
}