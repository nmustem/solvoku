package com.solvoku.solvokuservice.inbound.http

import com.solvoku.solvokuservice.domain.model.PuzzleNotFoundException
import com.solvoku.solvokuservice.domain.port.inbound.GetTodaysPuzzleUseCase
import com.solvoku.solvokuservice.inbound.http.mappers.toResponseDto
import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import kotlin.collections.mapOf


fun Route.puzzleRoutes(getTodaysPuzzle: GetTodaysPuzzleUseCase) {
    route("/v1/puzzles") {
        get("/today") {
            try {
                val puzzle = getTodaysPuzzle()
                call.respond(HttpStatusCode.OK, puzzle.toResponseDto())
            } catch (e: PuzzleNotFoundException) {
                call.respond(HttpStatusCode.NotFound, mapOf("error" to e.message))
            }
        }
    }
}

// TODO: think whether is worth it using handlers to have this routes file cleaner