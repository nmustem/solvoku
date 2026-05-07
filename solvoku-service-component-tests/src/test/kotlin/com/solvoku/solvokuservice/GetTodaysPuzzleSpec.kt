package com.solvoku.solvokuservice

import com.solvoku.solvokuservice.utils.JsonTestUtils
import io.kotest.matchers.shouldBe
import io.kotest.assertions.json.shouldEqualJson

class GetTodaysPuzzleSpec : SolvokuComponentSpec( {
    "should return 200 with the puzzle scheduled for today" {
        val response = application.makeGetRequest("/v1/puzzles/today")

        val expectedJson = JsonTestUtils.loadJsonFromResources("todays-puzzle-expected.json")
        response.statusCode shouldBe 200
        response.body shouldEqualJson expectedJson
    }
})
