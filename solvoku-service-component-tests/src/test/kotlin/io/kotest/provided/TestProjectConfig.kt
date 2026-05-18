package io.kotest.provided

import io.kotest.core.config.AbstractProjectConfig
import org.testcontainers.containers.PostgreSQLContainer

object PostgresTestContainer {
    lateinit var jdbcUrl: String
    lateinit var username: String
    lateinit var password: String

    private val container = PostgreSQLContainer<Nothing>("postgres:16").apply {
        withDatabaseName("solvoku_test")
        withUsername("solvoku")
        withPassword("solvoku")
    }

    fun start() {
        container.start()
        jdbcUrl   = container.jdbcUrl
        username  = container.username
        password  = container.password
    }

    fun stop() = container.stop()
}

object ProjectConfig : AbstractProjectConfig() {
    override val extensions = listOf(
        object : io.kotest.core.listeners.ProjectListener {
            override suspend fun beforeProject() { PostgresTestContainer.start() }
            override suspend fun afterProject() { PostgresTestContainer.stop() }
        }
    )
}