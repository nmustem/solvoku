package com.solvoku.solvokuservice

import com.solvoku.solvokuservice.inbound.http.puzzleRoutes
import com.solvoku.solvokuservice.server.health
import com.solvoku.solvokuservice.server.installStatusPages
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.routing

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    installSolvokuDI()
    installStatusPages()

    // Plugins
    install(ContentNegotiation) { json() }
    install(RoutingRoot)

    // Routes
    routing {
        health()
        puzzleRoutes()
    }
}
