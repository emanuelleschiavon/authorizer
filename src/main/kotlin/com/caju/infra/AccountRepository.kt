package com.caju.infra

import com.caju.infra.resources.AccountEntity
import jakarta.persistence.LockModeType.PESSIMISTIC_WRITE
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface AccountRepository : JpaRepository<AccountEntity, Long> {
    fun save(account: AccountEntity): AccountEntity

    @Lock(PESSIMISTIC_WRITE)
    @Query("SELECT a FROM AccountEntity a WHERE a.id = :id")
    fun findByIdWithPessimisticLock(id: Long): AccountEntity?
}
