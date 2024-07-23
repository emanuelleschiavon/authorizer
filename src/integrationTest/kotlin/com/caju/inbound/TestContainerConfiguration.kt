package com.caju.inbound

import org.junit.ClassRule
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.slf4j.LoggerFactory
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.GenericContainer
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.containers.wait.strategy.Wait
import org.testcontainers.utility.DockerImageName

abstract class TestContainerConfiguration {
    companion object {
        @JvmField
        @ClassRule
        val mySQLContainer: MySQLContainer<*> = MySQLContainer("mysql:latest")
            .withDatabaseName("caju")
            .withUsername("admin")
            .withPassword("admin")

        @JvmField
        @ClassRule
        val redis: GenericContainer<*> = GenericContainer(DockerImageName.parse("redis:latest"))
            .withExposedPorts(6379)
            .waitingFor(Wait.forListeningPort());

        @JvmStatic
        @DynamicPropertySource
        fun configureProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.data.redis.host", redis::getHost)
            registry.add("spring.data.redis.port") { redis.getMappedPort(6379).toString() }
            registry.add("spring.datasource.url", mySQLContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mySQLContainer::getUsername)
            registry.add("spring.datasource.password", mySQLContainer::getPassword)
        }
        @JvmStatic
        @BeforeAll
        fun beforeAll() {
            mySQLContainer.start()
            redis.start()
        }
        @JvmStatic
        @AfterAll
        fun afterAll() {
            mySQLContainer.stop()
            redis.stop()
        }
    }
}