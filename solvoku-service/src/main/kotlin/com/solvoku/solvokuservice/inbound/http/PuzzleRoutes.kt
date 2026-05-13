package com.solvoku.solvokuservice.inbound.http

import com.solvoku.solvokuservice.inbound.http.handlers.GetTodaysPuzzleHandler
import io.ktor.server.plugins.di.dependencies
import io.ktor.server.routing.Route
import io.ktor.server.routing.application
import io.ktor.server.routing.get
import io.ktor.server.routing.route


fun Route.puzzleRoutes() {
    val getTodaysPuzzleHandler: GetTodaysPuzzleHandler by application.dependencies

    route("/v1/puzzles") {
        get("/today") { getTodaysPuzzleHandler.handle(call) }
    }
}
