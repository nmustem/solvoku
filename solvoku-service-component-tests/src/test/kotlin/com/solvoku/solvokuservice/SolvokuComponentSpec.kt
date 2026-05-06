package com.solvoku.solvokuservice

import io.kotest.core.spec.style.FreeSpec
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.server.testing.TestApplication

data class HttpTestResponse(val statusCode: Int, val body: String)

abstract class SolvokuComponentSpec(body: SolvokuComponentSpec.() -> Unit) : FreeSpec() {

    private lateinit var testApp: TestApplication
    lateinit var application: TestApplicationWrapper

    init {
        beforeSpec {
            testApp = TestApplication {
                application { module() }
            }
            testApp.start()
            application = TestApplicationWrapper(testApp.createClient {})
        }

        afterSpec {
            testApp.stop()
        }

        body()
    }

    inner class TestApplicationWrapper(private val client: HttpClient) {
        suspend fun makeGetRequest(path: String): HttpTestResponse {
            val response = client.get(path)
            return HttpTestResponse(response.status.value, response.bodyAsText())
        }
    }
}
