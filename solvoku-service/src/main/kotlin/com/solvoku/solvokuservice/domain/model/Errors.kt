package com.solvoku.solvokuservice.domain.model

sealed class PlacementError {
    data class RowConflict(val row: Int, val suspectIds: List<String>) : PlacementError()
    data class ColConflict(val col: Int, val suspectIds: List<String>) : PlacementError()
    data class WrongPosition(val suspectId: String) : PlacementError()
}
