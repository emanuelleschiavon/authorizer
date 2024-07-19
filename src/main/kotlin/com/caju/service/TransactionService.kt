package com.caju.service

import com.caju.domain.BalanceMatcher
import com.caju.domain.TransactionEntity
import com.caju.infra.AccountEntity
import com.caju.infra.AccountRepository
import com.caju.infra.TransactionRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
) {
    @Transactional
    fun authorizer(transactionEntity: TransactionEntity) =
        accountRepository.findByIdOrNull(transactionEntity.accountId)?.toDomain()
            ?.let { BalanceMatcher(transactionEntity, it).updateBalance() }
            ?.let { accountRepository.save(AccountEntity(it)) }
            ?.let { transactionRepository.save(transactionEntity) }
            ?: throw RuntimeException("Account does not exist!")
}
