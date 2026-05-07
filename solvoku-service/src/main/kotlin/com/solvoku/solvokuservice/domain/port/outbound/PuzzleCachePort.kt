package com.solvoku.solvokuservice.domain.port.outbound

import com.solvoku.solvokuservice.domain.model.Puzzle

interface PuzzleCachePort {
    fun getTodaysPuzzle(): Puzzle?
    fun cacheTodaysPuzzle(puzzle: Puzzle)
    fun evict()
}
