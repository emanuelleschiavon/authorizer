package com.caju.infra.resources

import jakarta.persistence.*

@Entity(name = "TransactionEntity")
@Table(name = "transactions")
data class TransactionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    val accountId: Long,
    val amount: Double,
    val mcc: Int,
    val merchant: String,
)
