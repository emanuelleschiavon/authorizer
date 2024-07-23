package com.caju.inbound

import com.caju.domain.Account
import com.caju.infra.resources.AccountEntity
import com.caju.service.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AccountController(
    private val accountService: AccountService,
) {

    @PostMapping("/accounts")
    fun create(@RequestBody request: Account): ResponseEntity<Account> =
        accountService.save(AccountEntity(request))
            .let { ResponseEntity(it.toDomain(), HttpStatus.OK) }

}