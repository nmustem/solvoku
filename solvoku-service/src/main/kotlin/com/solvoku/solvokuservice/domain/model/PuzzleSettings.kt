package com.solvoku.solvokuservice.domain.model

data class CellPosition(val row: Int, val col: Int) {
    init {
        require(row >= 0) { "Row must be non-negative" }
        require(col >= 0) { "Col must be non-negative" }
    }
}

// enum class Theme { MURDER, GARDEN, INTERIOR, ZOO }
// enum class Difficulty { EASY, MEDIUM, HARD }