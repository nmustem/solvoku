package com.solvoku.solvokuservice.server

import com.solvoku.solvokuservice.outbound.db.PuzzlesTable
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.Application
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.transactions.transaction

fun Application.installDatabase() {
    val config = environment.config
    val dataSource = HikariDataSource(HikariConfig().apply {
        jdbcUrl      = config.property("database.url").getString()
        username     = config.property("database.user").getString()
        password     = config.property("database.password").getString()
        driverClassName = config.property("database.driver").getString()
        maximumPoolSize = 10
    })

    Database.connect(dataSource)

    transaction {
        SchemaUtils.create(PuzzlesTable)   // creates table only if it doesn't exist
    }
}
