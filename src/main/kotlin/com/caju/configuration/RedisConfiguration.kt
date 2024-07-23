package com.caju.configuration

import com.caju.infra.resources.Merchant
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate

@Configuration
class RedisConfiguration {

    @Bean
    fun redisTemplate(connectionFactory: RedisConnectionFactory): RedisTemplate<Merchant, Long> {
        val template: RedisTemplate<Merchant, Long> = RedisTemplate<Merchant, Long>()
        connectionFactory
        template.connectionFactory = connectionFactory
        return template
    }
}