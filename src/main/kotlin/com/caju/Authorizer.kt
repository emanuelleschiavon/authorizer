package com.caju

import com.caju.domain.ProductTypeProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories

@SpringBootApplication
@EnableRedisRepositories
@EnableConfigurationProperties(value = [ProductTypeProperties::class])
class Authorizer

fun main(args: Array<String>) {
    runApplication<Authorizer>(*args)
}
