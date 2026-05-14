package com.solvoku.solvokuservice.server

import com.solvoku.solvokuservice.SolvokuComponentSpec
import io.kotest.matchers.shouldBe

class HealthSpec : SolvokuComponentSpec() {
    init {
        "the application is healthy" {
            val response = application.makeGetRequest("/health")

            response.statusCode shouldBe 200
            response.body shouldBe "OK"
        }
    }
}
