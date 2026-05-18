package com.solvoku.solvokuservice

import com.solvoku.solvokuservice.outbound.db.PuzzlesTable
import com.solvoku.solvokuservice.utils.buildTestPuzzle
import com.solvoku.solvokuservice.utils.insertPuzzle
import io.kotest.matchers.shouldBe
import io.kotest.assertions.json.shouldEqualJson
import io.ktor.server.plugins.di.dependencies
import org.jetbrains.exposed.v1.jdbc.deleteAll
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class GetTodaysPuzzleSpec : SolvokuComponentSpec() {
    init {
        beforeEach {
            transaction { PuzzlesTable.deleteAll() }
            insertPuzzle(buildTestPuzzle(date = LocalDate.now()))
        }

        afterSpec {
            transaction { PuzzlesTable.deleteAll() }
        }

        "should return 200 with the puzzle scheduled for today" {
            val response = application.makeGetRequest("/v1/puzzles/today")

            response.statusCode shouldBe 200
        }
    }
}

class GetTodaysPuzzleNotFoundSpec : SolvokuComponentSpec(
    dependencyOverrides = {
        dependencies {
            provide<Clock> {
                Clock.fixed(Instant.parse("2099-01-01T00:00:00Z"), ZoneOffset.UTC)
            }
        }
    }
) {
    init {
        "should return 404 when today's puzzle is not found" {
            val response = application.makeGetRequest("/v1/puzzles/today")
            response.statusCode shouldBe 404
            response.body shouldEqualJson """{"errorMessage":"No puzzle scheduled for 2099-01-01", "errorCode":null}"""
        }
    }
}
