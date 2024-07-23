package com.caju.infra

import com.caju.domain.Account
import com.caju.infra.resources.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long> {
    fun save(account: Account): Account
}
