package com.caju.infra.resources

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed

@RedisHash("merchants")
data class Merchant(
    @Id
    val id: Long,
    @Indexed
    val name: String,
    val mcc: Int,
)
