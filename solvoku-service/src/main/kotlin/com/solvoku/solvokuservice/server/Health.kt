package com.solvoku.solvokuservice.server

import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.health() {
    get("/health") {
        call.respond("OK")
    }
}
