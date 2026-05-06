package com.solvoku

import com.solvoku.server.health
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.application.install
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.routing

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    install(RoutingRoot)

    routing {
        health()
    }
}

// TODO: afegir un health endpoint amb tests