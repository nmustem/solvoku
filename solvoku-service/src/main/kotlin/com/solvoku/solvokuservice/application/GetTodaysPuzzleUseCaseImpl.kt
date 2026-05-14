package com.solvoku.solvokuservice.application

import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.domain.model.PuzzleNotFoundException
import com.solvoku.solvokuservice.domain.port.inbound.GetTodaysPuzzleUseCase
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleCachePort
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleRepository
import java.time.Clock
import java.time.LocalDate

class GetTodaysPuzzleUseCaseImpl(
    private val clock: Clock = Clock.systemDefaultZone(),
    private val cache: PuzzleCachePort,
    private val repository: PuzzleRepository
) : GetTodaysPuzzleUseCase {

    override fun invoke(): Puzzle {
        val today = LocalDate.now(clock)

        return cache.getTodaysPuzzle()
            ?: repository.findByDate(today)
            ?: throw PuzzleNotFoundException(today)
    }
}
