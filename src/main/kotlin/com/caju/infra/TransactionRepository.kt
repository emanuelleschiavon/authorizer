package com.caju.infra

import com.caju.infra.resources.TransactionEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : JpaRepository<TransactionEntity, Long> {
    fun save(transactionEntity: TransactionEntity): TransactionEntity
}
