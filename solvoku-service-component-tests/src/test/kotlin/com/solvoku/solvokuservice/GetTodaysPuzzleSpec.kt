package com.solvoku.solvokuservice

import com.solvoku.solvokuservice.utils.JsonTestUtils
import io.kotest.matchers.shouldBe
import io.kotest.assertions.json.shouldEqualJson
import io.ktor.server.plugins.di.dependencies
import java.time.Clock
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneOffset

class GetTodaysPuzzleSpec : SolvokuComponentSpec() {
    init {
        "should return 200 with the puzzle scheduled for today" {
            // TODO: fix test, ofc the DB is empty
            // do we want to mock here the db and have integration tests for the db or just actual calls?
            val response = application.makeGetRequest("/v1/puzzles/today")

            val today = LocalDate.now().toString()
            // verify the rest of the response (skipping the date comparison)
            val expectedJson = JsonTestUtils.loadJsonFromResources("todays-puzzle-expected.json")
                .replace("\"date\": \"2026-05-07\"", "\"date\": \"$today\"")
            response.statusCode shouldBe 200
            response.body shouldEqualJson expectedJson
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
