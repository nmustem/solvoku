package com.solvoku.solvokuservice

import com.solvoku.solvokuservice.application.GetTodaysPuzzleUseCaseImpl
import com.solvoku.solvokuservice.domain.port.inbound.GetTodaysPuzzleUseCase
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleCachePort
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleRepository
import com.solvoku.solvokuservice.inbound.http.handlers.GetTodaysPuzzleHandler
import com.solvoku.solvokuservice.outbound.cache.NoOpPuzzleCache
import com.solvoku.solvokuservice.outbound.persistence.InMemoryPuzzleRepository
import io.ktor.server.application.Application
import io.ktor.server.plugins.di.dependencies
import java.time.Clock

fun Application.installSolvokuDI() {
    dependencies {
        provide<PuzzleRepository> { InMemoryPuzzleRepository() }
        provide<PuzzleCachePort> { NoOpPuzzleCache() }

        provide<Clock> { Clock.systemDefaultZone() }
        provide<GetTodaysPuzzleUseCase> { GetTodaysPuzzleUseCaseImpl(resolve(), resolve(), resolve()) }
        provide<GetTodaysPuzzleHandler> { GetTodaysPuzzleHandler(resolve()) }
    }
}
