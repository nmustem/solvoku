package com.solvoku.solvokuservice.application

import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.domain.model.PuzzleNotFoundException
import com.solvoku.solvokuservice.domain.port.inbound.GetTodaysPuzzleUseCase
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleCachePort
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleRepository
import java.time.LocalDate
import java.time.ZoneOffset

class GetTodaysPuzzleUseCaseImpl(
    private val cache: PuzzleCachePort,
    private val repository: PuzzleRepository
) : GetTodaysPuzzleUseCase {

    override fun invoke(): Puzzle {
        return cache.getTodaysPuzzle()
            ?: repository.findByDate(LocalDate.now(ZoneOffset.UTC))
            ?: throw PuzzleNotFoundException("No puzzle scheduled for today")
    }
}
