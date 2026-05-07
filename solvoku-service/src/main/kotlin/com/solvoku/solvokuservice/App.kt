package com.solvoku.solvokuservice

import com.solvoku.solvokuservice.application.GetTodaysPuzzleUseCaseImpl
import com.solvoku.solvokuservice.inbound.http.puzzleRoutes
import com.solvoku.solvokuservice.outbound.cache.NoOpPuzzleCache
import com.solvoku.solvokuservice.outbound.persistence.InMemoryPuzzleRepository
import com.solvoku.solvokuservice.server.health
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.routing.RoutingRoot
import io.ktor.server.routing.routing

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    // Outbound adapters
    val puzzleRepository = InMemoryPuzzleRepository()

    // Use cases
    val getTodaysPuzzle = GetTodaysPuzzleUseCaseImpl(
        cache = NoOpPuzzleCache(),   // TODO: no cache for now
        repository = puzzleRepository
    )

    // Plugins
    install(ContentNegotiation) { json() }
    install(RoutingRoot)

    // Routes
    routing {
        health()

        puzzleRoutes(getTodaysPuzzle)
    }
}
