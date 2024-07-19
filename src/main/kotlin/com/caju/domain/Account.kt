package com.caju.domain

data class Account(
    val id: Long,
    val balances: Map<ProductType, Double>
) {
    fun updateBalance(amountTransaction: Double, type: ProductType): Account {
        var typeToUse = type
        if (balances.getValue(type) < amountTransaction) {
            if (balances.getValue(ProductType.CASH) < amountTransaction){
                throw InsufficientFundsException()
            }
            typeToUse = ProductType.CASH
        }
        val balancesUpdated = balances.toMutableMap()
        balancesUpdated[typeToUse] = balances.getValue(typeToUse) - amountTransaction
        return this.copy(balances = balancesUpdated)

    }
}
