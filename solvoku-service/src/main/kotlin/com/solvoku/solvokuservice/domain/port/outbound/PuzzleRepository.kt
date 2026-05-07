package com.solvoku.solvokuservice.domain.port.outbound

import com.solvoku.solvokuservice.domain.model.Puzzle
import java.time.LocalDate
import java.util.UUID

interface PuzzleRepository {
    fun findByDate(date: LocalDate): Puzzle?
    // fun findById(id: UUID): Puzzle?
    // fun findAll(page: Int, size: Int): PuzzlePage
    // fun save(puzzle: Puzzle): Puzzle
}
