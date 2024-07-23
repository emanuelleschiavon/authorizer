package com.caju.service

import com.caju.domain.*
import com.caju.infra.resources.AccountEntity
import com.caju.infra.AccountRepository
import com.caju.infra.resources.TransactionEntity
import com.caju.infra.TransactionRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TransactionService(
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository,
    private val balanceMatcher: BalanceMatcher,
) {
    @Transactional
    fun authorizer(transactionEntity: TransactionEntity): TransactionEntity =
        accountRepository.findByIdWithPessimisticLock(transactionEntity.accountId)?.toDomain()
            ?.let { balanceMatcher.updateBalance(it, transactionEntity) }
            ?.let { accountRepository.save(AccountEntity(it)) }
            ?.let { transactionRepository.save(transactionEntity) }
            ?: throw RuntimeException("Account does not exist!")
}
