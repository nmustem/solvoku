package com.solvoku.solvokuservice.outbound.db

import com.solvoku.solvokuservice.utils.buildTestPuzzle
import com.solvoku.solvokuservice.utils.insertPuzzle
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.jetbrains.exposed.v1.jdbc.Database
import org.jetbrains.exposed.v1.jdbc.SchemaUtils
import org.jetbrains.exposed.v1.jdbc.deleteAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import org.testcontainers.containers.PostgreSQLContainer
import java.time.LocalDate

class PostgresPuzzleRepositoryTest : FreeSpec({

    val container = PostgreSQLContainer<Nothing>("postgres:16").apply {
        withDatabaseName("solvoku_test")
        withUsername("solvoku")
        withPassword("solvoku")
    }

    beforeSpec {
        container.start()
        Database.connect(
            url      = container.jdbcUrl,
            driver   = "org.postgresql.Driver",
            user     = container.username,
            password = container.password
        )
        transaction { SchemaUtils.create(PuzzlesTable) }
    }

    afterSpec { container.stop() }

    beforeEach { transaction { PuzzlesTable.deleteAll() } }

    val repository = PostgresPuzzleRepository()

    "findByDate" - {
        "returns puzzle when it exists for the given date" {
            val puzzle = buildTestPuzzle(date = LocalDate.now())
            insertPuzzle(puzzle)

            val result = repository.findByDate(LocalDate.now())

            result shouldNotBe null
            result!!.id shouldBe puzzle.id
            result.title shouldBe puzzle.title
            result.zones shouldBe puzzle.zones
            result.suspects shouldBe puzzle.suspects
            result.victim shouldBe puzzle.victim
            result.killerId shouldBe puzzle.killerId
        }

        "returns null when no puzzle exists for the given date" {
            val result = repository.findByDate(LocalDate.of(2099, 1, 1))

            result shouldBe null
        }

        "returns null when a puzzle exists but for a different date" {
            insertPuzzle(buildTestPuzzle(date = LocalDate.now().minusDays(1)))

            val result = repository.findByDate(LocalDate.now())

            result shouldBe null
        }
    }
})
