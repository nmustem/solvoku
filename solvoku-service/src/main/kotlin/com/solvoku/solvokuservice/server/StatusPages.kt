package com.solvoku.solvokuservice.server

import com.solvoku.solvokuservice.domain.model.PuzzleNotFoundException
import com.solvoku.solvokuservice.inbound.http.model.ErrorResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.installStatusPages() {
    install(StatusPages) {
        exception<PuzzleNotFoundException> { call, cause ->
            call.respond(
                HttpStatusCode.NotFound,
                ErrorResponse(
                    errorMessage = cause.message ?: "Not found",
                )
            )
        }
    }
}
