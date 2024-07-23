package com.caju.infra.resources

import com.caju.domain.Account
import com.caju.domain.ProductType
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity(name = "AccountEntity")
@Table(name = "accounts")
data class AccountEntity(
    @Id
    val id: Long,
    val balanceFood: Double,
    val balanceMeal: Double,
    val balanceCash: Double,
) {
    constructor(account: Account) : this(
        id = account.id,
        balanceFood = account.balances[ProductType.FOOD] ?: throw RuntimeException("Unexpected Error"),
        balanceMeal = account.balances[ProductType.MEAL] ?: throw RuntimeException("Unexpected Error"),
        balanceCash = account.balances[ProductType.CASH] ?: throw RuntimeException("Unexpected Error"),
    )
    fun toDomain() = Account(
        id = this.id,
        balances = mapOf(
            ProductType.FOOD to balanceFood,
            ProductType.MEAL to balanceMeal,
            ProductType.CASH to balanceCash,
        )
    )
}