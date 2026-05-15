package com.solvoku.solvokuservice

import io.kotest.core.spec.style.FreeSpec
import io.kotest.provided.PostgresTestContainer
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.server.application.Application
import io.ktor.server.config.MapApplicationConfig
import io.ktor.server.testing.TestApplication

data class HttpTestResponse(val statusCode: Int, val body: String)

abstract class SolvokuComponentSpec(
    private val dependencyOverrides: Application.() -> Unit = {}
) : FreeSpec() {

    private lateinit var testApp: TestApplication
    lateinit var application: TestApplicationWrapper

    init {
        beforeSpec {
            testApp = TestApplication {
                environment {
                    config = MapApplicationConfig(
                        "database.url" to PostgresTestContainer.jdbcUrl,
                        "database.user" to PostgresTestContainer.username,
                        "database.password" to PostgresTestContainer.password,
                        "database.driver" to "org.postgresql.Driver"
                    )
                }
                application {
                    dependencyOverrides()
                    module()
                }
            }
            testApp.start()
            application = TestApplicationWrapper(testApp.createClient {})
        }
        afterSpec { testApp.stop() }
    }

    inner class TestApplicationWrapper(private val client: HttpClient) {
        suspend fun makeGetRequest(path: String): HttpTestResponse {
            val response = client.get(path)
            return HttpTestResponse(response.status.value, response.bodyAsText())
        }
    }
}
