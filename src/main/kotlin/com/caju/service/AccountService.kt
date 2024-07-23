package com.caju.service

import com.caju.infra.AccountRepository
import com.caju.infra.resources.AccountEntity
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
) {

    fun save(account: AccountEntity): AccountEntity =
        accountRepository.save(account)

}
