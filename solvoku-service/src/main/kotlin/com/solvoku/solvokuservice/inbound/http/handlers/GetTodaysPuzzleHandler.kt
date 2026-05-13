package com.solvoku.solvokuservice.inbound.http.handlers

import com.solvoku.solvokuservice.domain.port.inbound.GetTodaysPuzzleUseCase
import com.solvoku.solvokuservice.inbound.http.mappers.toResponseDto
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

class GetTodaysPuzzleHandler (private val getTodaysPuzzle: GetTodaysPuzzleUseCase) {
    suspend fun handle(call: ApplicationCall) {
        val puzzle = getTodaysPuzzle()

        // TODO: we need to handle PuzzleNotFoundException somewhere and then have a StatusPages that converts exceptions
        // to responses: call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))

        call.respond(HttpStatusCode.OK, puzzle.toResponseDto())
    }
}
