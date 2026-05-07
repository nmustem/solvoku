package com.solvoku.solvokuservice.outbound.cache

import com.solvoku.solvokuservice.domain.model.Puzzle
import com.solvoku.solvokuservice.domain.port.outbound.PuzzleCachePort

// Placeholder — swap for Redis adapter later without touching any other layer
class NoOpPuzzleCache : PuzzleCachePort {
    override fun getTodaysPuzzle(): Puzzle? = null
    override fun cacheTodaysPuzzle(puzzle: Puzzle) = Unit
    override fun evict() = Unit
}
