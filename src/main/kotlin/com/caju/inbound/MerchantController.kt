package com.caju.inbound

import com.caju.infra.resources.Merchant
import com.caju.service.MerchantService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class MerchantController(
    private val merchantService: MerchantService,
) {

    @PostMapping("/merchants")
    fun create(@RequestBody request: Merchant): ResponseEntity<Merchant> =
        merchantService.save(request)
            .let { ResponseEntity(it, HttpStatus.OK) }

    @GetMapping("/merchants/{name}")
    fun get(@PathVariable name: String): ResponseEntity<Merchant> =
        merchantService.getByName(name)
            .let { ResponseEntity(it, HttpStatus.OK) }
}